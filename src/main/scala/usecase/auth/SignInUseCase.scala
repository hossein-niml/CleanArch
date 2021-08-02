package usecase.auth

import contract.service.auth._
import contract.callback.auth._

class SignInUseCase(thisRep: UserCallback) extends SignInService {
  val rep: UserCallback = thisRep

  override def call(req: SignInService.Request): Unit = {
    rep.signIn(req.username, req.password)
  }
}

object SignInUseCase {
  def apply(thisRep: UserCallback): SignInUseCase = {
    new SignInUseCase(thisRep)
  }
}