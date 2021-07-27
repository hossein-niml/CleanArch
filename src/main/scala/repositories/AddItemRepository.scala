package repositories

import entities.Item
import datebase._

class AddItemRepository(dataStore: DB) extends Callback.AddItemCallback {
  override val dataBase: DB = dataStore
  override def addItem(body: String, state: Boolean): Unit = {
    dataBase.addItem(Item(body, state))
  }
}

object AddItemRepository {
  def apply(dataStore: DB): AddItemRepository = {
    new AddItemRepository(dataStore)
  }
}
