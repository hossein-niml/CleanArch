package contract.service.todo

import contract.service.Service

abstract class EditBodyService extends Service[EditBodyService.Request, Unit]

object EditBodyService {
  case class Request(userId: Int, id: Int, newBody: String)
}