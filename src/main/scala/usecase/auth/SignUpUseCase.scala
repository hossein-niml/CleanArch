package usecase.auth

import modules.exceptions._
import contract.service.auth._
import contract.callback.auth._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class SignUpUseCase(userCallback: UserCallback, sessionCallback: SessionCallback) extends SignUpService {

  override def call(req: SignUpService.Request)(implicit ec: ExecutionContext): Future[Unit] = for {
    userOption <- userCallback.getByName(req.username)
    _ <- userOption match {
      case Some(_) => Future.failed(ExceptionsModule.reSignUp(req.username))
      case _ => userCallback.add(req.username, req.password)
    }
    newUserOption <- userCallback.getByName(req.username)
    result <- newUserOption match {
      case None => Future.failed(ExceptionsModule.userNotFound)
      case Some(user) => sessionCallback.add(user.id)
    }
  } yield result

}
