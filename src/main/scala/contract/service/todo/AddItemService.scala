package contract.service.todo

import contract.service.Service

abstract class AddItemService extends Service[AddItemService.Request, Unit]

object AddItemService {
  case class Request(userId:Int, body: String, state: Boolean)
}