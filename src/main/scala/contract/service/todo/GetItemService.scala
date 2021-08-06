package contract.service.todo

import contract.service.Service
import domain.todo._
import scala.util.Try

abstract class GetItemService extends Service[GetItemService.Request, Try[Item]]

object GetItemService {
  case class Request(userId: Int, id: Int)
}
