package contract.service.todo

import contract.service.Service
import domain.todo.Item


abstract class EditBodyService extends Service[EditBodyService.Request, Map[Long, Item]]

object EditBodyService {

  case class Request(userId: Long, id: Long, newBody: String)

}
