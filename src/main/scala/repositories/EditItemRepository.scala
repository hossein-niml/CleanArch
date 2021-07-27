package repositories

import datebase._

class EditItemRepository(dataStore: DataBase) extends Callback.EditItemCallback {
  override val dataBase: DataBase = dataStore
  override def editBody(id: Int, newBody: String): Unit = {
    dataBase.editBody(id, newBody)
  }

  override def editState(id: Int, newState: Boolean): Unit = {
    dataBase.editState(id, newState)
  }
}

object EditItemRepository {
  def apply(dataStore: DataBase): EditItemRepository = {
    new EditItemRepository(dataStore)
  }
}