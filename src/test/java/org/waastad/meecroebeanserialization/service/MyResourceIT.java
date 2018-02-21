/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.meecroebeanserialization.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.client.ClientBuilder;
import lombok.extern.log4j.Log4j2;
import org.apache.meecrowave.Meecrowave;
import org.junit.Test;
import org.waastad.meecroebeanserialization.domain.Item;
import org.waastad.meecroebeanserialization.domain.User;
import org.waastad.meecroebeanserialization.json.EbeanJsonWriter;

/**
 *
 * @author helge
 */
@Log4j2
public class MyResourceIT {

   public MyResourceIT() {
   }

   /**
    * Test of get method, of class MyResource.
    */
   @Test
   public void testGet() throws Exception {
      int numberOfClients = 10;
      Meecrowave.Builder builder = new Meecrowave.Builder();
      builder.randomHttpPort();
      builder.setJaxrsDefaultProviders(EbeanJsonWriter.class.getName());

      try (Meecrowave meecrowave = new Meecrowave(builder).bake()) {
         meecrowave.inject(this);
         User user = User.builder().name("username").age(40).build();
         Item item = Item.builder().itemName("itemname").build();
         user.addItem(item);
         user.save();

         ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
         CountDownLatch startSignal = new CountDownLatch(1);
         CountDownLatch doneSignal = new CountDownLatch(numberOfClients);
         for (int i = 1; i <= numberOfClients; i++) {
            log.info("Running call.....");
//            List<Customer> customers = cm.getCustomers();
            executor.execute(new MyRunnable(startSignal, doneSignal, meecrowave.getConfiguration().getHttpPort()));
         }
         startSignal.countDown();
         doneSignal.await(60, TimeUnit.SECONDS);
         executor.shutdownNow();
         log.info("===Done===");

//         log.info(Ebean.json().toJson(user));
//         String get = ClientBuilder.newClient().target("http://localhost:" + meecrowave.getConfiguration().getHttpPort() + "/user").request().get(String.class);
//         // String get = ClientBuilder.newClient().register(LoggingFeature.class).target("http://localhost:" + meecrowave.getConfiguration().getHttpPort() + "/user").request().get(String.class);
//         log.info("GOT: {}", get);
      }
   }

   private class MyRunnable implements Runnable {

      private CountDownLatch startSignal;
      private CountDownLatch doneSignal;
      private int port;

      public MyRunnable(CountDownLatch startSignal, CountDownLatch doneSignal, int port) {
         this.startSignal = startSignal;
         this.doneSignal = doneSignal;
         this.port = port;
      }

      @Override
      public void run() {
         try {
            startSignal.await();
            ClientBuilder.newClient().target("http://localhost:" + port + "/user").request().get(String.class);
//            WebClient.client(cm).header("my-uuid", UUID.randomUUID().toString());
//            cm.getCustomers();
            doneSignal.countDown();
         } catch (InterruptedException e) {

         }
      }

      private void doInvoke() {

      }

   }
   
   @Test
   public void testDeleteCascade() throws Exception {
      Meecrowave.Builder builder = new Meecrowave.Builder();
      builder.randomHttpPort();
      try (Meecrowave meecrowave = new Meecrowave(builder).bake()) {
         meecrowave.inject(this);
         User user = User.builder().name("username").age(40).build();
         Item item = Item.builder().itemName("itemname").build();
         user.addItem(item);
         user.save();
         
         user.delete();
      }
   }

}
