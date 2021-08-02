package contract.service.auth

import contract.service._

abstract class SignInService extends Service[SignInService.Request, Unit]

object SignInService {
  case class Request(username: String, password: String)
}