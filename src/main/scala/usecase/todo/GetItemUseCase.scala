package usecase.todo

import contract.callback.todo._
import contract.service.todo._
import domain.todo._

class GetItemUseCase(thisRep: ItemCallback) extends GetItemService {
  override val rep: ItemCallback = thisRep
  override def call(req: GetItemService.Request): Item = {
    rep.get(req.id)
  }
}

object GetItemUseCase {
  def apply(thisRep: ItemCallback): GetItemUseCase = {
    new GetItemUseCase(thisRep)
  }
}