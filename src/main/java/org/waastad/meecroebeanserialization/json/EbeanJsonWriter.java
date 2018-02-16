/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.meecroebeanserialization.json;

import io.ebean.Ebean;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author helge
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class EbeanJsonWriter implements MessageBodyWriter<Object> {

   @Override
   public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
      return true;
   }

   @Override
   public long getSize(Object t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
      return 0;
   }

   @Override
   public void writeTo(Object t, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
      Writer writer = new BufferedWriter(new OutputStreamWriter(out));
      Ebean.json().toJson(t, writer);
   }

}
