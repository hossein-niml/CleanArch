package contract.callback.todo

import domain.todo._

abstract class ItemCallback {
  def add(userId: Int, body: String, state: Boolean): Unit

  def get(userId: Int): Option[Map[Int, Item]]

  def editBody(userId: Int, id: Int, newBody: String): Map[Int, Item]

  def editState(userId: Int, id: Int, newState: Boolean): Map[Int, Item]
}