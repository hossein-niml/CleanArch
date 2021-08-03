package usecase.auth

import modules.exceptions._
import contract.service.auth._
import contract.callback.auth._

class SignUpUseCase(thisRep: UserCallback) extends SignUpService {
  val rep: UserCallback = thisRep

  override def call(req: SignUpService.Request): Unit = {
    val user = rep.getByName(req.username)
    user match {
      case Some(_) => throw Exceptions.reSignUp(req.username)
      case _ => rep.add(req.username, req.password)
    }
  }
}

object SignUpUseCase {
  def apply(thisRep: UserCallback): SignUpUseCase = {
    new SignUpUseCase(thisRep)
  }
}