package usecase.auth

import contract.service.auth._
import contract.callback.auth._
import domain.auth._
import modules.exceptions._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class SignInUseCase(userCallback: UserCallback, sessionCallback: SessionCallback) extends SignInService {

  override def call(req: SignInService.Request)(implicit ec: ExecutionContext): Future[Session] = for {
    userOption <- userCallback.getByName(req.username)
    user <- userOption match {
      case Some(user) => Future.successful(user)
      case None => Future.failed(ExceptionsModule.invalidUserName)
    }
    sessionOption <- sessionCallback.getById(user.id)
    session <- sessionOption match {
      case Some(session) => Future.successful(session)
      case None => Future.failed(ExceptionsModule.invalidUserName)
    }
    result <- if (session.isLogin) {
      Future.failed(ExceptionsModule.reSignIn(req.username))
    } else if (user.password != req.password) {
      Future.failed(ExceptionsModule.invalidPassword)
    } else {
      sessionCallback.update(session.setLogin(true))
    }
  } yield result

}
