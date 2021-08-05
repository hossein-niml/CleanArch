package usecase.todo

import modules.exceptions._
import contract.callback.todo._
import contract.service.todo._
import domain.todo._

class GetItemUseCase(itemCallback: ItemCallback) extends GetItemService {

  override def call(req: GetItemService.Request): Option[Item] = {
    val items = itemCallback.get(req.userId).getOrElse(throw Exceptions.userNotFound)
    items.get(req.id)
  }

}
