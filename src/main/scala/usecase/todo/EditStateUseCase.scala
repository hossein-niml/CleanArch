package usecase.todo

import contract.callback.todo._
import contract.service.todo._

class EditStateUseCase(thisRep: ItemCallback) extends EditStateService {
  override val rep: ItemCallback = thisRep
  override def call(req: EditStateService.Req): Unit = {
    rep.editState(req.id, req.newState)
  }
}

object EditStateUseCase {
  def apply(thisRep: ItemCallback): EditStateUseCase = {
    new EditStateUseCase(thisRep)
  }
}