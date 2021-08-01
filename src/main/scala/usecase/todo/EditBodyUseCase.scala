package usecase.todo

import contract.callback.todo._
import contract.service.todo._

class EditBodyUseCase(thisRep: ItemCallback) extends EditBodyService {
  override val rep: ItemCallback = thisRep
  override def call(req: EditBodyService.Request): Unit = {
    rep.editBody(req.id, req.newBody)
  }
}

object EditBodyUseCase {
  def apply(thisRep: ItemCallback): EditBodyUseCase = {
    new EditBodyUseCase(thisRep)
  }
}