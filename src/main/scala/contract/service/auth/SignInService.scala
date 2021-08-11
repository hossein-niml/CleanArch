package contract.service.auth

import contract.service._
import domain.auth.Session

abstract class SignInService extends Service[SignInService.Request, Session]

object SignInService {

  case class Request(username: String, password: String)

}
