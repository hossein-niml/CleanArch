package repositories

import entities.Item
import datebase._

class GetItemRepository(dataStore: DB) extends Callback.GetItemCallback {
  override val dataBase: DB = dataStore
  override def getItem(id: Int): Item = {
    dataBase.getItem(id)
  }
}

object GetItemRepository {
  def apply(dataStore: DB): GetItemRepository = {
    new GetItemRepository(dataStore)
  }
}
