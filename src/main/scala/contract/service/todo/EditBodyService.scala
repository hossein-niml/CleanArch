package contract.service.todo

import contract.service.Service
import domain.todo.Item


abstract class EditBodyService extends Service[EditBodyService.Request, Map[Int, Item]]

object EditBodyService {

  case class Request(userId: Int, id: Int, newBody: String)

}
