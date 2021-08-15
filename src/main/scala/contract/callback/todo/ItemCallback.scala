package contract.callback.todo

import domain.todo._
import scala.concurrent.Future

abstract class ItemCallback {

  def add(userId: Long, body: String, state: Boolean): Future[Map[Long, Item]]

  def getById(userId: Long): Future[Option[Map[Long, Item]]]

  def editBody(userId: Long, id: Long, newBody: String): Future[Map[Long, Item]]

  def editState(userId: Long, id: Long, newState: Boolean): Future[Map[Long, Item]]

}
