package contract.callback.auth

import domain.auth._

abstract class UserCallback {

  def add(username: String, password: String): Unit

  def getSessionById(id: Int): Option[Session]

  def getUserByName(username: String): Option[User]

  def remove(id: Int): Unit

  def updateUser(user: User): User

  def updateSession(id: Int, session: Session): Session

}
