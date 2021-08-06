package contract.callback.auth

import domain.auth._
import scala.util.Try

abstract class UserCallback {

  def add(username: String, password: String): Try[Unit]

  def getSessionById(id: Int): Try[Session]

  def getUserByName(username: String): Try[User]

  def remove(id: Int): Try[Unit]

  def updateUser(user: User): Try[User]

  def updateSession(id: Int, session: Session): Try[Session]

}
