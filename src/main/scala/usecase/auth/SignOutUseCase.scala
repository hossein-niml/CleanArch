package usecase.auth

import modules.exceptions._
import contract.service.auth._
import contract.callback.auth._

class SignOutUseCase(thisRep: UserCallback) extends SignOutService {
  val rep: UserCallback = thisRep

  override def call(req: SignOutService.Request): Unit = {
    val sessionOption = rep.getById(req.userID)
    sessionOption match {
      case Some(session) => if(session.isLogin) rep.signOut(req.userID) else throw Exceptions.reSignOut(req.userID)
      case _ => throw Exceptions.userNotFound
    }
  }
}

object SignOutUseCase {
  def apply(thisRep: UserCallback): SignOutUseCase = {
    new SignOutUseCase(thisRep)
  }
}