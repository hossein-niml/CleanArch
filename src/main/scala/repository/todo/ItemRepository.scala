package repository.todo

import contract.callback.todo._
import modules.database.DataBase
import domain.todo._

class ItemRepository(dataStore: DataBase) extends ItemCallback {
  override val dataBase: DataBase = dataStore

  override def add(userId: Int, body: String, state: Boolean): Unit = {
    dataBase.addItem(userId, Item(body, state))
  }

  override def get(userId: Int, id: Int): Option[Item] = {
    dataBase.getItem(userId, id)
  }

  override def editBody(userId: Int, id: Int, newBody: String): Unit = {
    dataBase.editBody(userId, id, newBody)
  }

  override def editState(userId: Int, id: Int, newState: Boolean): Unit = {
    dataBase.editState(userId, id, newState)
  }
}

object ItemRepository {
  def apply(dataStore: DataBase): ItemRepository = {
    new ItemRepository(dataStore)
  }
}
