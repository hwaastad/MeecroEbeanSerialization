package org.waastad.meecroebeanserialization.domain.finder;

import io.ebean.Finder;
import java.util.List;
import org.waastad.meecroebeanserialization.domain.User;
import org.waastad.meecroebeanserialization.domain.query.QUser;

public class UserFinder extends Finder<Integer, User> {

   /**
    * Construct using the default EbeanServer.
    */
   public UserFinder() {
      super(User.class);
   }

   /**
    * Construct with a given EbeanServer.
    */
   public UserFinder(String serverName) {
      super(User.class, serverName);
   }

   /**
    * Start a new typed query.
    */
   public QUser where() {
      return new QUser(db());
   }

   /**
    * Start a new document store query.
    */
   public QUser text() {
      return new QUser(db()).text();
   }

   public List<User> findUsersWithFetch() {
      return new QUser().userItems.fetch().findList();
   }
   
   public User findUserByName(String name) {
      return new QUser().name.eq(name).findOne();
   }
}
