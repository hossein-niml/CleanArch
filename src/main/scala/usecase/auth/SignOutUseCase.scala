package usecase.auth

import contract.service.auth._
import contract.callback.auth._

class SignOutUseCase(thisRep: UserCallback) extends SignOutService {
  val rep: UserCallback = thisRep

  override def call(req: SignOutService.Request): Unit = {
    rep.signOut(req.userID)
  }
}

object SignOutUseCase {
  def apply(thisRep: UserCallback): SignOutUseCase = {
    new SignOutUseCase(thisRep)
  }
}