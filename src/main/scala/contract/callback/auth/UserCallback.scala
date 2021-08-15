package contract.callback.auth

import domain.auth._
import scala.concurrent.Future


abstract class UserCallback {

  def add(username: String, password: String): Future[Unit]

  def getByName(username: String): Future[Option[User]]

  def remove(id: Long): Future[Unit]

  def update(user: User): Future[User]

}
