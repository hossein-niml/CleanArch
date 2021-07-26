package repositories

import entities.Item
import datebase._

class AddItemRepository(_dataBase: DB) extends AddItemCallback {
  override val dataBase: DB = _dataBase
  override def addItem(item: Item): Unit = {
    dataBase.addItem(item)
  }
}

object AddItemRepository {
  def apply(dataBase: DB): AddItemRepository = {
    new AddItemRepository(dataBase)
  }
}
