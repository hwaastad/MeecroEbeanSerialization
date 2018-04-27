/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.meecroebeanserialization.domain;

import io.ebean.Model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;

/**
 * @author helge
 */
@MappedSuperclass
@Data
public abstract class BaseModel extends Model {

   @Id
   private Integer id;
   @Version
   private Long version;

}
