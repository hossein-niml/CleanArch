package contract.service.auth

import contract.service._
import scala.util.Try

abstract class SignInService extends Service[SignInService.Request, Try[Unit]]

object SignInService {
  case class Request(username: String, password: String)
}