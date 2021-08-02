package contract.service.todo

import contract.service.Service
import domain.todo._

abstract class GetItemService extends Service[GetItemService.Request, Option[Item]]

object GetItemService {
  case class Request(userId: Int, id: Int)
}
