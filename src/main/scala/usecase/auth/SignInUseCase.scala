package usecase.auth

import contract.service.auth._
import contract.callback.auth._
import modules.exceptions._
import scala.util.Try


class SignInUseCase(rep: UserCallback) extends SignInService {

  override def call(req: SignInService.Request): Try[Unit] = Try {
    val user = rep.getUserByName(req.username).getOrElse(throw Exceptions.invalidUserName)
    val session = rep.getSessionById(user.id).getOrElse(throw Exceptions.userNotFound)
    if (session.isLogin) throw Exceptions.reSignIn(req.username)
    if (user.password != req.password) throw Exceptions.invalidPassword
    rep.updateSession(user.id, session.setLogin(true))
  }

}
