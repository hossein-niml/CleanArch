package usecase.auth

import modules.exceptions._
import contract.service.auth._
import contract.callback.auth._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class SignUpUseCase(rep: UserCallback) extends SignUpService {

  override def call(req: SignUpService.Request)(implicit ec: ExecutionContext): Future[Unit] = for {
    userOption <- rep.getUserByName(req.username)
    result <- userOption match {
      case Some(_) => Future.failed(Exceptions.reSignUp(req.username))
      case _ => rep.add(req.username, req.password)
    }
  } yield result

}
