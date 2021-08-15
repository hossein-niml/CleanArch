package contract.service.todo

import contract.service.Service
import domain.todo.Item


abstract class EditStateService extends Service[EditStateService.Request, Map[Long, Item]]

object EditStateService {

  case class Request(userId: Long, id: Long, newState: Boolean)

}
