package repositories

import entities.Item
import datebase._

class AddItemRepository(dataStore: DataBase) extends Callback.AddItemCallback {
  override val dataBase: DataBase = dataStore
  override def addItem(body: String, state: Boolean): Unit = {
    dataBase.addItem(Item(body, state))
  }
}

object AddItemRepository {
  def apply(dataStore: DataBase): AddItemRepository = {
    new AddItemRepository(dataStore)
  }
}
