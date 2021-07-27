package usecases

import entities._
import repositories.Callback

sealed abstract class Service {
  val rep: Callback
}

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