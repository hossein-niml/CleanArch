package contract.service.todo

import contract.service.Service

abstract class EditStateService extends Service[EditStateService.Request, Unit]

object EditStateService {
  case class Request(userId: Int, id: Int, newState: Boolean)
}