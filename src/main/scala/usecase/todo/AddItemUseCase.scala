package usecase.todo

import contract.callback.todo._
import contract.service.todo._

class AddItemUseCase(itemCallback: ItemCallback) extends AddItemService {

  override def call(req: AddItemService.Request): Unit = {
    itemCallback.add(req.userId, req.body, req.state)
  }

}
