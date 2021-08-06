package contract.service.auth

import contract.service._
import scala.util.Try

abstract class SignUpService extends Service[SignUpService.Request, Try[Unit]]

object SignUpService {
  case class Request(username: String, password: String)
}