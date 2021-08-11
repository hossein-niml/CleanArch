package contract.service.todo

import contract.service.Service
import domain.todo.Item

abstract class AddItemService extends Service[AddItemService.Request, Map[Int, Item]]

object AddItemService {

  case class Request(userId: Int, body: String, state: Boolean)

}
