package modules.database

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

trait DataBase[T] {

  implicit val ec: ExecutionContext = DataBase.ec

  var map: Map[Int, T] = Map.empty

  var lastNewId: Int = 1

  def get(id: Int): Option[T] = {
    map.get(id)
  }

  def add(item: T): Future[Unit] = Future {
    map synchronized {
      map = map + (lastNewId -> item)
      lastNewId = lastNewId + 1
    }
  }

  def update(id: Int, item: T): Future[T] = Future {
    map synchronized {
      map = map + (id -> item)
    }
    item
  }

  def delete(id: Int): Future[Unit] = Future {
    map synchronized {
      map = map.-(id)
    }
  }

}

object DataBase {

  val ec: ExecutionContext = ExecutionContext fromExecutor Executors.newCachedThreadPool()

}