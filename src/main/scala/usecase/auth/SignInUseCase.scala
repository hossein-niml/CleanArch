package usecase.auth

import contract.service.auth._
import contract.callback.auth._
import modules.exceptions._

class SignInUseCase(thisRep: UserCallback) extends SignInService {
  val rep: UserCallback = thisRep

  override def call(req: SignInService.Request): Unit = {
    val user = rep.getByName(req.username).getOrElse(throw Exceptions.invalidUserName)
    if (user.password != req.password) {
      throw Exceptions.invalidPassword
    } else {
      rep.signIn(user.id)
    }
  }
}

object SignInUseCase {
  def apply(thisRep: UserCallback): SignInUseCase = {
    new SignInUseCase(thisRep)
  }
}