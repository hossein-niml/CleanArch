package usecase.auth

import modules.exceptions._
import contract.service.auth._
import contract.callback.auth._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class SignOutUseCase(sessionCallback: SessionCallback) extends SignOutService {

  override def call(req: SignOutService.Request)(implicit ec: ExecutionContext): Future[Unit] = for {
    sessionOption <- sessionCallback.getById(req.userID)
    _ <- sessionOption match {
      case Some(session) =>
        if (session.isLogin) {
          sessionCallback.update(session.setLogin(false))
        } else {
          Future.failed(ExceptionsModule.reSignOut(req.userID))
        }
      case _ => Future.failed(ExceptionsModule.userNotFound)
    }
  } yield ()

}
