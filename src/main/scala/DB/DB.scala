package DB

import Entities._

class DB {
  private var itemsMap: Map[Int, Item] = Map.empty

  private def findNewId(i: Int): Int = {
    if (itemsMap.contains(i)) findNewId(i + 1) else i
  }

  def getItem(id: Int) = itemsMap(id)

  def addItem(item: Item): Unit = {
    val newId = findNewId(1)
    val newItemsMap = itemsMap + (newId -> item)
    itemsMap = newItemsMap
  }

  def editMsg(id: Int, newMsg: String): Unit = {
    val prevItem = itemsMap(id)
    val newItemsMap = itemsMap.-(id) + (id -> prevItem.copy(newMsg, prevItem.state))
    itemsMap = newItemsMap
  }

  def editState(id: Int, newState: Boolean): Unit = {
    val prevItem = itemsMap(id)
    val newItemsMap = itemsMap.-(id) + (id -> prevItem.copy(prevItem.body, newState))
    itemsMap = newItemsMap
  }

}

object DB extends DB