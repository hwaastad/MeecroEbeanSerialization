/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.meecroebeanserialization.domain;

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

   private String itemName;
   @ManyToOne
   private User owner;

}
