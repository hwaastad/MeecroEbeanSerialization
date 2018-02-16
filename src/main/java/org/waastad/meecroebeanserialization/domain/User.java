/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.meecroebeanserialization.domain;

import io.ebean.Finder;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author helge
 */
@Entity
@Data
@Builder
public class User extends BaseModel {

   private String name;
   private int age;
   @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<Item> userItems;

   public void addItem(Item i) {
      if (this.userItems == null) {
         this.userItems = new ArrayList<>();
      }
      this.userItems.add(i);
   }

}
