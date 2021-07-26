package Repositories

import Entities.Item
import DB._

class AddItemRepository extends AddItemCallback {
  override def addItem(item: Item): Unit = {
    DB.addItem(item)
  }
}

object AddItemRepository extends AddItemRepository
