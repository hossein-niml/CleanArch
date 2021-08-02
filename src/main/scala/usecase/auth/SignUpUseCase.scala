package usecase.auth

import contract.service.auth._
import contract.callback.auth._

class SignUpUseCase(thisRep: UserCallback) extends SignUpService {
  val rep: UserCallback = thisRep

  override def call(req: SignUpService.Request): Unit = {
    rep.add(req.username, req.password)
  }
}

object SignUpUseCase {
  def apply(thisRep: UserCallback): SignUpUseCase = {
    new SignUpUseCase(thisRep)
  }
}