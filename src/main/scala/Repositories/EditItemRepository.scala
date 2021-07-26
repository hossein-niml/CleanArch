package Repositories

import Entities.Item
import DB._

class EditItemRepository extends EditItemCallback {
  override def editMsg(id: Int, newMsg: String): Unit = {
    DB.editMsg(id, newMsg)
  }

  override def editState(id: Int, newState: Boolean): Unit = {
    DB.editState(id, newState)
  }
}

object EditItemRepository extends EditItemRepository