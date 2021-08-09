package contract.service.todo

import contract.service.Service
import scala.util.Try

abstract class AddItemService extends Service[AddItemService.Request, Try[Unit]]

object AddItemService {

  case class Request(userId: Int, body: String, state: Boolean)

}
