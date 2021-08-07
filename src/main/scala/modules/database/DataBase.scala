package modules.database

import modules.exceptions._
import scala.annotation.tailrec
import scala.util.Try


class DataBase[T] {

  var map: Map[Int, T] = Map.empty

  def findNewId(map: Map[Int, _]): Int = {
    @tailrec
    def findNewIdHelper(i: Int): Int = {
      if (map.contains(i)) findNewIdHelper(i + 1) else i
    }

    findNewIdHelper(1)
  }

  def get(id: Int): Try[T] = Try {
    map.getOrElse(id, throw Exceptions.notFound)
  }

  def add(item: T): Try[Unit] = Try {
    val newId = findNewId(map)
    map = map + (newId -> item)
  }

  def update(id: Int, item: T): Try[T] = Try {
    map = map + (id -> item)
    item
  }

  def delete(id: Int): Try[Unit] = Try {
    map = map.-(id)
  }

}
