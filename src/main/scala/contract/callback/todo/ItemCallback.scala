package contract.callback.todo

import domain.todo._
import modules.database._

abstract class ItemCallback {
  val dataBase: DataBase

  def add(userId: Int, body: String, state: Boolean): Unit

  def get(userId: Int, id: Int): Option[Item]

  def editBody(userId: Int, id: Int, newBody: String): Unit

  def editState(userId: Int, id: Int, newState: Boolean): Unit
}