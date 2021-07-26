package Repositories

import Entities.Item
import DB._

class GetItemRepository extends GetItemCallback {
  override def getItem(id: Int): Item = {
    DB.getItem(id)
  }
}

object GetItemRepository extends GetItemRepository
