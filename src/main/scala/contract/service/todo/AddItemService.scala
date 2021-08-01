package contract.service.todo

import contract.service.Service

abstract class AddItemService extends Service[AddItemService.Req, Unit]

object AddItemService {
  case class Req(body: String, state: Boolean)
}