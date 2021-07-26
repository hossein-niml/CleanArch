package UseCase

import Entities._

abstract class Service

abstract class AddItemService extends Service{
  def addItem(item: Item): Unit
}

abstract class GetItemService extends Service {
  def getItem(id: Int): Item
}

abstract class EditItemService extends Service {
  def editMsg(id: Int, newMsg: String): Unit
  def editState(id: Int, newState: Boolean): Unit
}