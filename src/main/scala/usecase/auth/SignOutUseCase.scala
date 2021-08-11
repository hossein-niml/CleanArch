package usecase.auth

import modules.exceptions._
import contract.service.auth._
import contract.callback.auth._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class SignOutUseCase(rep: UserCallback) extends SignOutService {

  override def call(req: SignOutService.Request)(implicit ec: ExecutionContext): Future[Unit] = for {
    sessionOption <- rep.getSessionById(req.userID)
    result <- sessionOption match {
      case Some(session) =>
        if (session.isLogin) {
          rep.updateSession(req.userID, session.setLogin(false))
        } else {
          Future.failed(Exceptions.reSignOut(req.userID))
        }
      case _ => Future.failed(Exceptions.userNotFound)
    }
  } yield result

}
