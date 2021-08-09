package contract.service.todo

import contract.service.Service
import domain.todo.Item
import scala.util.Try

abstract class EditStateService extends Service[EditStateService.Request, Try[Map[Int, Item]]]

object EditStateService {

  case class Request(userId: Int, id: Int, newState: Boolean)

}
