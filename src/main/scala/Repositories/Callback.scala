package Repositories

import Entities._

abstract class Callback

abstract class AddItemCallback extends Callback {
  def addItem(item: Item): Unit
}

abstract class GetItemCallback extends Callback {
  def getItem(id: Int): Item
}

abstract class EditItemCallback extends Callback {
  def editMsg(id: Int, newMsg: String): Unit
  def editState(id: Int, newState: Boolean): Unit
}
