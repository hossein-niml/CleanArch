package repositories

import datebase._

class EditItemRepository(_dataBase: DB) extends EditItemCallback {
  override val dataBase: DB = _dataBase
  override def editMsg(id: Int, newMsg: String): Unit = {
    dataBase.editMsg(id, newMsg)
  }

  override def editState(id: Int, newState: Boolean): Unit = {
    dataBase.editState(id, newState)
  }
}

object EditItemRepository {
  def apply(dataBase: DB): EditItemRepository = {
    new EditItemRepository(dataBase)
  }
}