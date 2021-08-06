package contract.service.auth

import contract.service._
import scala.util.Try

abstract class SignOutService extends Service[SignOutService.Request, Try[Unit]]

object SignOutService {
  case class Request(userID: Int)
}