package usecase.auth

import contract.service.auth._
import contract.callback.auth._
import domain.auth._
import modules.exceptions._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class SignInUseCase(rep: UserCallback) extends SignInService {

  override def call(req: SignInService.Request)(implicit ec: ExecutionContext): Future[Session] = for {
    userOption <- rep.getUserByName(req.username)
    user <- userOption match {
      case Some(user) => Future.successful(user)
      case None => Future.failed(Exceptions.invalidUserName)
    }
    sessionOption <- rep.getSessionById(user.id)
    session <- sessionOption match {
      case Some(session) => Future.successful(session)
      case None => Future.failed(Exceptions.invalidUserName)
    }
    result <- if (session.isLogin) {
      Future.failed(Exceptions.reSignIn(req.username))
    } else if (user.password != req.password) {
      Future.failed(Exceptions.invalidPassword)
    } else {
      rep.updateSession(user.id, session.setLogin(true))
    }
  } yield result

}
