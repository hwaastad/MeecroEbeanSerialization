/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.meecroebeanserialization.db;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import io.ebean.config.dbplatform.h2.H2Platform;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import lombok.extern.log4j.Log4j2;
import org.waastad.meecroebeanserialization.domain.User;

/**
 * @author helge
 */
@WebListener
@Log4j2
public class EbeanInitializer implements ServletContextListener {

   @Inject
   private DataSource ds;

   private EbeanServer ebeanServer;

   @Override
   public void contextDestroyed(ServletContextEvent sce) {
      log.info("Shutting down eBean Server....");
      this.ebeanServer.shutdown(true, true);
   }

   @Override
   public void contextInitialized(ServletContextEvent sce) {
      log.info("Initializing eBean Server....");
      ServerConfig config = new ServerConfig();
      config.loadFromProperties();
      config.setDataSource(ds);
      config.setName("default-server");
      config.setAutoCommitMode(false);
      config.setDatabasePlatform(new H2Platform());
      config.setRegister(true);
      config.setDefaultServer(true);
      config.setDdlGenerate(true);
      config.setDdlRun(true);
      config.getAutoTuneConfig().setProfiling(false);
      config.getAutoTuneConfig().setQueryTuning(false);
      config.addPackage(User.class.getPackage().getName());
      this.ebeanServer = EbeanServerFactory.create(config);
   }

}
