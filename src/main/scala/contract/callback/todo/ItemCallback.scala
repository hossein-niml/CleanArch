package contract.callback.todo

import domain.todo._
import scala.util.Try

abstract class ItemCallback {
  def add(userId: Int, body: String, state: Boolean): Try[Unit]

  def get(userId: Int): Try[Map[Int, Item]]

  def editBody(userId: Int, id: Int, newBody: String): Try[Map[Int, Item]]

  def editState(userId: Int, id: Int, newState: Boolean): Try[Map[Int, Item]]
}