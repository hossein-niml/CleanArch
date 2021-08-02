package contract.callback.auth

import domain.auth._
import modules.database._

abstract class UserCallback {
  val dataBase: DataBase

  def add(username: String, password: String): Unit

  def getById(id: Int): Option[Session]

  def getByName(username: String): Option[User]

  def remove(id: Int): Unit

  def update(user: User): Unit

  def signIn(id: Int): Unit

  def signOut(id: Int): Unit
}
