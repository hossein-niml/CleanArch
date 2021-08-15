package contract.callback.auth

import domain.auth._
import scala.concurrent.Future

abstract class SessionCallback {

  def add(userId: Long): Future[Unit]

  def getById(userId: Long): Future[Option[Session]]

  def remove(id: Long): Future[Unit]

  def update(session: Session): Future[Session]

}
