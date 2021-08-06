package usecase.auth

import modules.exceptions._
import contract.service.auth._
import contract.callback.auth._
import scala.util.{Success, Try}

class SignOutUseCase(rep: UserCallback) extends SignOutService {

  override def call(req: SignOutService.Request): Try[Unit] = Try {
    val sessionTry = rep.getSessionById(req.userID)
    sessionTry match {
      case Success(session) => if (session.isLogin) rep.updateSession(req.userID, session.setLogin(false)) else throw Exceptions.reSignOut(req.userID)
      case _ => throw Exceptions.userNotFound
    }
  }
}
