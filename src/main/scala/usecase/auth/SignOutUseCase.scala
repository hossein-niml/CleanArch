package usecase.auth

import modules.exceptions._
import contract.service.auth._
import contract.callback.auth._

class SignOutUseCase(rep: UserCallback) extends SignOutService {

  override def call(req: SignOutService.Request): Unit = {
    val sessionOption = rep.getSessionById(req.userID)
    sessionOption match {
      case Some(session) => if (session.isLogin) rep.updateSession(req.userID, session.setLogin(false)) else throw Exceptions.reSignOut(req.userID)
      case _ => throw Exceptions.userNotFound
    }
  }
}
