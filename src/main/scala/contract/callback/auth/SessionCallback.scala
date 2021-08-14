package contract.callback.auth

import domain.auth._
import scala.concurrent.Future

abstract class SessionCallback {

  def add(userId: Int): Future[Unit]

  def getById(userId: Int): Future[Option[Session]]

  def remove(id: Int): Future[Unit]

  def update(session: Session): Future[Session]

}
