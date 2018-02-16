/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.meecroebeanserialization.domain;

import io.ebean.Ebean;
import io.ebean.text.json.EJson;
import javax.json.Json;
import lombok.extern.log4j.Log4j2;
import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;
import org.junit.Test;

/**
 *
 * @author helge
 */
@Log4j2
public class SerializationTest {

   public SerializationTest() {
   }

   /**
    * Test of getName method, of class User.
    */
   @Test(expected = StackOverflowError.class)
   public void testGetName() throws Exception {
      System.out.println("getName");
      User u = User.builder().name("John").build();
      u.setId(1);
      u.setName("John");
      Item i = Item.builder().build();
      i.setId(1);
      i.setItemName("itemname");
      i.setOwner(u);
      u.addItem(i);

      Mapper build = new MapperBuilder().build();
      build.writeObjectAsString(u);
//     System.out.println(EJson.write(u));
   }

}
