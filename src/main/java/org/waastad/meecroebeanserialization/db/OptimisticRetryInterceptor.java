/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.meecroebeanserialization.db;

import java.io.Serializable;
import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.OptimisticLockException;

import lombok.extern.log4j.Log4j2;

@Interceptor
@Retry(on = OptimisticLockException.class)
@Priority(Interceptor.Priority.APPLICATION)
@Log4j2
public class OptimisticRetryInterceptor implements Serializable {

   @AroundInvoke
   public Object beginAndCommit(InvocationContext ic) throws Throwable {
      Retry annotation = ic.getMethod().getAnnotation(Retry.class);
      int times = annotation.times();
      Class retryOn = annotation.on();
      assert times > 0;
      int numAttempts = 0;
      do {
         numAttempts++;
         try {
            return ic.proceed();
         } catch (Throwable t) {
            log.info("Optimistic locking detected, {} remaining retries on {}", times - numAttempts, retryOn.getName());
            if (!retryOn.isInstance(t)) {
               throw t;
            }
         }
      } while (numAttempts <= times);
      log.info("end interceptor");
      throw new IllegalStateException("Insert failed....");
   }
}
