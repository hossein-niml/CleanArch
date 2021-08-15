package modules.database

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

trait DataBase[T] {

  implicit val ec: ExecutionContext = DataBase.ec

  var map: Map[Long, T] = Map.empty

  var lastNewId: Long = 1

  def get(id: Long): Option[T] = {
    map.get(id)
  }

  def add(item: T): Future[Unit] = Future {
    map synchronized {
      map = map + (lastNewId -> item)
      lastNewId = lastNewId + 1
    }
  }

  def update(id: Long, item: T): Future[T] = Future {
    map synchronized {
      map = map + (id -> item)
    }
    item
  }

  def delete(id: Long): Future[Unit] = Future {
    map synchronized {
      map = map.-(id)
    }
  }

}

object DataBase {

  val ec: ExecutionContext = ExecutionContext fromExecutor Executors.newCachedThreadPool()

}