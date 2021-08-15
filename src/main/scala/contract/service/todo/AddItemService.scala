package contract.service.todo

import contract.service.Service
import domain.todo.Item

abstract class AddItemService extends Service[AddItemService.Request, Map[Long, Item]]

object AddItemService {

  case class Request(userId: Long, body: String, state: Boolean)

}
