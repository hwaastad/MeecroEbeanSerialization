/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.meecroebeanserialization.repository;

import io.ebean.annotation.Transactional;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.OptimisticLockException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomUtils;
import org.waastad.meecroebeanserialization.db.Retry;
import org.waastad.meecroebeanserialization.domain.User;

@Dependent
@Log4j2
public class Repository {

   public List<User> findUsers() {
      log.info("Returning fetch Users");
      return User.find.findUsersWithFetch();
   }

   public void updateUser(User user) {
      log.info("Updating user");
      user.save();
   }

   public User findUserByName(String name) {
      log.info("Lookup user");
      return User.find.findUserByName(name);
   }

   @Transactional
   @Retry(on = OptimisticLockException.class, times = 10)
   public void doAComplexComputationOnUser(String name) {
      User findUserByName = findUserByName("username");
      findUserByName.setAge(RandomUtils.nextInt());
      updateUser(findUserByName);
   }
}
