package usecase.todo

import contract.callback.todo._
import contract.callback.auth._
import contract.service.todo._
import domain.todo.Item
import modules.exceptions.Exceptions

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class GetItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends GetItemService {

  override def call(req: GetItemService.Request)(implicit ec: ExecutionContext): Future[Item] = for {
    sessionOption <- userCallback.getSessionById(req.userId)
    session <- sessionOption match {
      case Some(session) => Future.successful(session)
      case None => Future.failed(Exceptions.userNotFound)
    }
    itemsOption <- itemCallback.get(req.userId)
    item <- itemsOption match {
      case Some(itemsMap) =>
        val itemOption = itemsMap.get(req.id)
        itemOption match {
          case Some(item) => Future.successful(item)
          case None => Future.failed(Exceptions.itemNotFound)
        }
      case None => Future.failed(Exceptions.itemNotFound)
    }
  } yield item

}
