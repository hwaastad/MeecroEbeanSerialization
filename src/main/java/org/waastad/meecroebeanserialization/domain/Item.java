/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.meecroebeanserialization.domain;

import org.waastad.meecroebeanserialization.domain.finder.ItemFinder;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author helge
 */
@Entity
@Data
@Builder
public class Item extends BaseModel {

  public static final ItemFinder find = new ItemFinder();

   private String itemName;
   @ManyToOne
   private User owner;

}
