package usecase.todo

import contract.callback.todo._
import contract.service.todo._
import domain.todo.Item
import scala.util.Try

class EditBodyUseCase(itemCallback: ItemCallback) extends EditBodyService {

  override def call(req: EditBodyService.Request): Try[Map[Int, Item]] = {
    itemCallback.editBody(req.userId, req.id, req.newBody)
  }

}
