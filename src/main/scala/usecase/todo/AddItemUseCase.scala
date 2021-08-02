package usecase.todo

import contract.callback.auth._
import contract.callback.todo._
import contract.service.todo._

class AddItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends AddItemService {
  val itemRep: ItemCallback = itemCallback
  val userRep: UserCallback = userCallback

  override def call(req: AddItemService.Request): Unit = {
    val user = userRep.get(req.userId)
    user match {
      case Some(session) if session.isLogin => itemRep.add(req.userId, req.body, req.state)
      case _ => throw new ClassNotFoundException()
    }
  }
}

object AddItemUseCase {
  def apply(itemCallback: ItemCallback, userCallback: UserCallback): AddItemUseCase = {
    new AddItemUseCase(itemCallback, userCallback)
  }
}