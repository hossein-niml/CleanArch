package repositories

import entities.Item
import datebase._

class GetItemRepository(dataStore: DataBase) extends Callback.GetItemCallback {
  override val dataBase: DataBase = dataStore
  override def getItem(id: Int): Item = {
    dataBase.getItem(id)
  }
}

object GetItemRepository {
  def apply(dataStore: DataBase): GetItemRepository = {
    new GetItemRepository(dataStore)
  }
}
