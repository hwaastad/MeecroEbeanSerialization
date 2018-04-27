/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.meecroebeanserialization.db;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.h2.Driver;

/**
 * @author helge
 */
@ApplicationScoped
@Log4j2
public class DataSourceProducer {

   @Produces
   DataSource getDataSource() {
      final BasicDataSource source = new BasicDataSource();
      source.setDriver(new Driver());
      source.setMaxTotal(16);
      source.setMinIdle(5);
      source.setMaxIdle(10);
      source.setMaxOpenPreparedStatements(100);
      source.setDefaultAutoCommit(Boolean.FALSE);
      source.setUsername("sa");
      source.setPassword("");
      source.setUrl("jdbc:h2:mem:tests");
      log.info("Producing datasource: {}", source.getUrl());
      return source;
   }
}
