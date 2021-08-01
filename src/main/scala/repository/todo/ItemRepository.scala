package repository.todo

import contract.callback.todo._
import modules.database.DataBase
import domain.todo._

class ItemRepository(dataStore: DataBase) extends ItemCallback {
  override val dataBase: DataBase = dataStore

  override def add(body: String, state: Boolean): Unit = {
    dataBase.addItem(Item(body, state))
  }

  override def get(id: Int): Item = {
    dataBase.getItem(id)
  }

  override def editBody(id: Int, newBody: String): Unit = {
    dataBase.editBody(id, newBody)
  }

  override def editState(id: Int, newState: Boolean): Unit = {
    dataBase.editState(id, newState)
  }
}

object ItemRepository {
  def apply(dataStore: DataBase): ItemRepository = {
    new ItemRepository(dataStore)
  }
}