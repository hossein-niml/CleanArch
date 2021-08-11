package contract.service.todo

import contract.service.Service
import domain.todo.Item


abstract class EditStateService extends Service[EditStateService.Request, Map[Int, Item]]

object EditStateService {

  case class Request(userId: Int, id: Int, newState: Boolean)

}
