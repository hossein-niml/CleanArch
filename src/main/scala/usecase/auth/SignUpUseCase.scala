package usecase.auth

import modules.exceptions._
import contract.service.auth._
import contract.callback.auth._

class SignUpUseCase(rep: UserCallback) extends SignUpService {

  override def call(req: SignUpService.Request): Unit = {
    val user = rep.getUserByName(req.username)
    user match {
      case Some(_) => throw Exceptions.reSignUp(req.username)
      case _ => rep.add(req.username, req.password)
    }
  }

}