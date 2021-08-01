package contract.service.todo

import contract.service.Service

abstract class EditBodyService extends Service[EditBodyService.Req, Unit]

object EditBodyService {
  case class Req(id: Int, newBody: String)
}