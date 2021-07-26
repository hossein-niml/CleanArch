package repositories

import entities.Item
import datebase._

class GetItemRepository(_dataBase: DB) extends GetItemCallback {
  override val dataBase: DB = _dataBase
  override def getItem(id: Int): Item = {
    dataBase.getItem(id)
  }
}

object GetItemRepository {
  def apply(dataBase: DB): GetItemRepository = {
    new GetItemRepository(dataBase)
  }
}
