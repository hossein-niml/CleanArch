package contract.service.todo

import contract.service.Service
import domain.todo.Item
import scala.util.Try

abstract class EditBodyService extends Service[EditBodyService.Request, Try[Map[Int, Item]]]

object EditBodyService {
  case class Request(userId: Int, id: Int, newBody: String)
}