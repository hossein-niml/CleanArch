package usecase.todo

import modules.exceptions._
import contract.callback.todo._
import contract.service.todo._
import domain.todo._
import scala.util.Try

class GetItemUseCase(itemCallback: ItemCallback) extends GetItemService {

  override def call(req: GetItemService.Request): Try[Item] = Try {
    itemCallback.get(req.userId).getOrElse(throw Exceptions.userNotFound)(req.id)
  }

}
