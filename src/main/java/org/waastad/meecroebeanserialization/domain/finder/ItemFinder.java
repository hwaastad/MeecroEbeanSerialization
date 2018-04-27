package org.waastad.meecroebeanserialization.domain.finder;

import io.ebean.Finder;
import org.waastad.meecroebeanserialization.domain.Item;
import org.waastad.meecroebeanserialization.domain.query.QItem;

public class ItemFinder extends Finder<Integer, Item> {

   /**
    * Construct using the default EbeanServer.
    */
   public ItemFinder() {
      super(Item.class);
   }

   /**
    * Construct with a given EbeanServer.
    */
   public ItemFinder(String serverName) {
      super(Item.class, serverName);
   }

   /**
    * Start a new typed query.
    */
   public QItem where() {
      return new QItem(db());
   }

   /**
    * Start a new document store query.
    */
   public QItem text() {
      return new QItem(db()).text();
   }
}
