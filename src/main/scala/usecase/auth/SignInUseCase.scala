package usecase.auth

import contract.service.auth._
import contract.callback.auth._
import modules.exceptions._

class SignInUseCase(thisRep: UserCallback) extends SignInService {
  val rep: UserCallback = thisRep

  override def call(req: SignInService.Request): Unit = {
    val user = rep.getByName(req.username).getOrElse(throw Exceptions.invalidUserName)
    val isLogin = rep.getById(user.id).getOrElse(throw Exceptions.userNotFound).isLogin
    if (isLogin) throw Exceptions.reSignIn(req.username)
    if (user.password != req.password) throw Exceptions.invalidPassword
    rep.signIn(user.id)
  }
}

object SignInUseCase {
  def apply(thisRep: UserCallback): SignInUseCase = {
    new SignInUseCase(thisRep)
  }
}