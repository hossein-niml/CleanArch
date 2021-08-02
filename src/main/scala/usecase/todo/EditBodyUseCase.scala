package usecase.todo

import modules.exceptions._
import contract.callback.auth._
import contract.callback.todo._
import contract.service.todo._


class EditBodyUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends EditBodyService {
  val itemRep: ItemCallback = itemCallback
  val userRep: UserCallback = userCallback

  override def call(req: EditBodyService.Request): Unit = {
    val user = userRep.getById(req.userId)
    user match {
      case Some(session) if session.isLogin => itemRep.editBody(req.userId, req.id, req.newBody)
      case _ => throw Exceptions.userNotFound
    }
  }
}

object EditBodyUseCase {
  def apply(itemCallback: ItemCallback, userCallback: UserCallback): EditBodyUseCase = {
    new EditBodyUseCase(itemCallback, userCallback)
  }
}