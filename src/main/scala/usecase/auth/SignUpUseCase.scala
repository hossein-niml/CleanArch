package usecase.auth

import modules.exceptions._
import contract.service.auth._
import contract.callback.auth._
import scala.util.Try
import scala.util.Success

class SignUpUseCase(rep: UserCallback) extends SignUpService {

  override def call(req: SignUpService.Request): Try[Unit] = Try {
    val userTry = rep.getUserByName(req.username)
    userTry match {
      case Success(_) => throw Exceptions.reSignUp(req.username)
      case _ => rep.add(req.username, req.password)
    }
  }

}
