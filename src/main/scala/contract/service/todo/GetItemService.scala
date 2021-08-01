package contract.service.todo

import contract.service.Service
import domain.todo._

abstract class GetItemService extends Service[GetItemService.Req, Item]

object GetItemService {
  case class Req(id: Int)
}
