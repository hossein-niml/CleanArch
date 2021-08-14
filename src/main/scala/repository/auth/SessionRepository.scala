package repository.auth

import contract.callback.auth._
import modules.database._
import domain.auth._

import scala.concurrent.Future

class SessionRepository extends SessionCallback with DataBase[Session] {

  override def add(userId: Int): Future[Unit] = {
    val session = Session(userId, isLogin = false)
    add(session)
  }

  override def getById(userId: Int): Future[Option[Session]] = Future {
    get(userId)
  }

  override def remove(id: Int): Future[Unit] = {
    delete(id)
  }

  override def update(session: Session): Future[Session] = {
    update(session.userId, session)
  }

}
