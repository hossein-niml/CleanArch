package contract.callback.auth

import domain.auth._
import modules.database._

abstract class UserCallback {
  val dataBase: DataBase

  def add(username: String, password: String): Unit

  def get(id: Int): Option[Session]

  def remove(id: Int): Unit

  def update(user: User): Unit

  def signIn(username: String, password: String): Unit

  def signOut(id: Int): Unit
}
