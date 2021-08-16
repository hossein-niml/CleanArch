package usecase.todo

import contract.callback.todo._
import contract.callback.auth._
import contract.service.todo._
import domain.todo.Item
import modules.exceptions.ExceptionsModule

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class AddItemUseCase(itemCallback: ItemCallback, sessionCallback: SessionCallback) extends AddItemService {

  override def call(req: AddItemService.Request)(implicit ec: ExecutionContext): Future[Map[Long, Item]] = for {
    sessionOption <- sessionCallback.getById(req.userId)
    session <- sessionOption match {
      case Some(_) => itemCallback.add(req.userId, req.body, req.state)
      case None => Future.failed(ExceptionsModule.userNotFound)
    }
  } yield session

}
