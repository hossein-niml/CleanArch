package usecase.todo

import modules.exceptions._
import contract.callback.auth._
import contract.callback.todo._
import contract.service.todo._

class EditStateUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends EditStateService {
  val itemRep: ItemCallback = itemCallback
  val userRep: UserCallback = userCallback

  override def call(req: EditStateService.Request): Unit = {
    val user = userRep.getById(req.userId)
    user match {
      case Some(session) if session.isLogin => itemRep.editState(req.userId, req.id, req.newState)
      case _ => throw Exceptions.userNotFound
    }
  }
}

object EditStateUseCase {
  def apply(itemCallback: ItemCallback, userCallback: UserCallback): EditStateUseCase = {
    new EditStateUseCase(itemCallback, userCallback)
  }
}