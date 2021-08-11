package contract.callback.auth

import domain.auth._
import scala.concurrent.Future


abstract class UserCallback {

  def add(username: String, password: String): Future[Unit]

  def getSessionById(id: Int): Future[Option[Session]]

  def getUserByName(username: String): Future[Option[User]]

  def remove(id: Int): Future[Unit]

  def updateUser(user: User): Future[User]

  def updateSession(id: Int, session: Session): Future[Session]

}
