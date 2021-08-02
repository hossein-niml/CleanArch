package usecase.todo

import contract.callback.auth._
import contract.callback.todo._
import contract.service.todo._
import domain.todo._

class GetItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends GetItemService {
  val itemRep: ItemCallback = itemCallback
  val userRep: UserCallback = userCallback

  override def call(req: GetItemService.Request): Option[Item] = {
    val user = userRep.get(req.userId)
    user match {
      case Some(session) if session.isLogin => itemRep.get(req.userId, req.id)
      case _ => throw new ClassNotFoundException()
    }
  }
}

object GetItemUseCase {
  def apply(itemCallback: ItemCallback, userCallback: UserCallback): GetItemUseCase = {
    new GetItemUseCase(itemCallback, userCallback)
  }
}