package contract.callback.todo

import domain.todo._
import scala.concurrent.Future

abstract class ItemCallback {

  def add(userId: Int, body: String, state: Boolean): Future[Map[Int, Item]]

  def getById(userId: Int): Future[Option[Map[Int, Item]]]

  def editBody(userId: Int, id: Int, newBody: String): Future[Map[Int, Item]]

  def editState(userId: Int, id: Int, newState: Boolean): Future[Map[Int, Item]]

}
