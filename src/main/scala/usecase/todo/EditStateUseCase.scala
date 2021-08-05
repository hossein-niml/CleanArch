package usecase.todo

import contract.callback.todo._
import contract.service.todo._
import domain.todo.Item

class EditStateUseCase(itemCallback: ItemCallback) extends EditStateService {

  override def call(req: EditStateService.Request): Map[Int, Item] = {
    itemCallback.editState(req.userId, req.id, req.newState)
  }

}
