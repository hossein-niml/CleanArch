package repositories

import datebase._

class EditItemRepository(dataStore: DB) extends Callback.EditItemCallback {
  override val dataBase: DB = dataStore
  override def editBody(id: Int, newBody: String): Unit = {
    dataBase.editBody(id, newBody)
  }

  override def editState(id: Int, newState: Boolean): Unit = {
    dataBase.editState(id, newState)
  }
}

object EditItemRepository {
  def apply(dataStore: DB): EditItemRepository = {
    new EditItemRepository(dataStore)
  }
}