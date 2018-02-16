/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.meecroebeanserialization.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.waastad.meecroebeanserialization.repository.Repository;

/**
 *
 * @author helge
 */
@ApplicationScoped
@Path("/user")
public class MyResource {

   @Inject
   private Repository repository;

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response get() {
      repository.doAComplexComputationOnUser("username");
      return Response.ok(repository.findUsers()).build();
   }
}
