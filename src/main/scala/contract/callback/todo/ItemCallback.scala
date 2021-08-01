package contract.callback.todo

import domain.todo._
import modules.database._

abstract class ItemCallback {
  val dataBase: DataBase

  def add(body: String, state: Boolean): Unit

  def get(id: Int): Item

  def editBody(id: Int, newBody: String): Unit

  def editState(id: Int, newState: Boolean): Unit
}