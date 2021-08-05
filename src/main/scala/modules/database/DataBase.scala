package modules.database

import modules.exceptions._
import scala.annotation.tailrec


class DataBase[T] {

  var map: Map[Int, T] = Map.empty

  def findNewId(map: Map[Int, _]): Int = {
    @tailrec
    def findNewIdHelper(i: Int): Int = if (map.contains(i)) findNewIdHelper(i + 1) else i

    findNewIdHelper(1)
  }

  def get(id: Int): Option[T] = {
    map.get(id)
  }

  def add(item: T): Unit = {
    val newId = findNewId(map)
    map = map + (newId -> item)
  }

  def update(id: Int, item: T): T = {
    map = map + (id -> item)
    item
  }

  def delete(id: Int): Unit = {
    map = map.-(id)
  }
}
