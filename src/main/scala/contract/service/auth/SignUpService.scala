package contract.service.auth

import contract.service._

abstract class SignUpService extends Service[SignUpService.Request, Unit]

object SignUpService {
  case class Request(username: String, password: String)
}