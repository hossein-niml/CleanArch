package datebase

import entities._

import scala.annotation.tailrec

abstract class DB {
  def getItem(id: Int): Item

  def addItem(item: Item): Unit

  def editMsg(id: Int, newMsg: String): Unit

  def editState(id: Int, newState: Boolean): Unit
}

class myDataBase extends DB {
  private var itemsMap: Map[Int, Item] = Map.empty

  @tailrec
  private def findNewId(i: Int): Int = {
    if (itemsMap.contains(i)) findNewId(i + 1) else i
  }

  override def getItem(id: Int): Item = itemsMap(id)

  override def addItem(item: Item): Unit = {
    val newId = findNewId(1)
    val newItemsMap = itemsMap + (newId -> item)
    itemsMap = newItemsMap
  }

  override def editMsg(id: Int, newMsg: String): Unit = {
    val prevItem = itemsMap(id)
    val newItemsMap = itemsMap.-(id) + (id -> Item(newMsg, prevItem.getState()))
    itemsMap = newItemsMap
  }

  override def editState(id: Int, newState: Boolean): Unit = {
    val prevItem = itemsMap(id)
    val newItemsMap = itemsMap.-(id) + (id -> Item(prevItem.getBody(), newState))
    itemsMap = newItemsMap
  }

}

object myDataBase extends myDataBase