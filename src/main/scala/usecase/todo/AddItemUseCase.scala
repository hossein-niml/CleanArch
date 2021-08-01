package usecase.todo

import contract.callback.todo._
import contract.service.todo._

class AddItemUseCase(thisRep: ItemCallback) extends AddItemService {
  override val rep: ItemCallback = thisRep
  override def call(req: AddItemService.Req): Unit = {
    rep.add(req.body, req.state)
  }
}

object AddItemUseCase {
  def apply(thisRep: ItemCallback): AddItemUseCase = {
    new AddItemUseCase(thisRep)
  }
}