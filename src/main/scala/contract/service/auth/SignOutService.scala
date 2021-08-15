package contract.service.auth

import contract.service._

abstract class SignOutService extends Service[SignOutService.Request, Unit]

object SignOutService {

  case class Request(userID: Long)

}
