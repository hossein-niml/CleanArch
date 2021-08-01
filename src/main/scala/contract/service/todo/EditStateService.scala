package contract.service.todo

import contract.service.Service

abstract class EditStateService extends Service[EditStateService.Req, Unit]

object EditStateService {
  case class Req(id: Int, newState: Boolean)
}