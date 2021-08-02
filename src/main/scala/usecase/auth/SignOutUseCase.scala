package usecase.auth

import modules.exceptions._
import contract.service.auth._
import contract.callback.auth._

class SignOutUseCase(thisRep: UserCallback) extends SignOutService {
  val rep: UserCallback = thisRep

  override def call(req: SignOutService.Request): Unit = {
    val userOption = rep.getById(req.userID)
    userOption match {
      case Some(_) => rep.signOut(req.userID)
      case _ => throw Exceptions.userNotFound
    }
  }
}

object SignOutUseCase {
  def apply(thisRep: UserCallback): SignOutUseCase = {
    new SignOutUseCase(thisRep)
  }
}