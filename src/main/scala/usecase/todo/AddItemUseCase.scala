package usecase.todo

import contract.callback.todo._
import contract.callback.auth._
import contract.service.todo._
import domain.todo.Item
import modules.exceptions.Exceptions

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class AddItemUseCase(itemCallback: ItemCallback, sessionCallback: SessionCallback) extends AddItemService {

  override def call(req: AddItemService.Request)(implicit ec: ExecutionContext): Future[Map[Int, Item]] = for {
    sessionOption <- sessionCallback.getById(req.userId)
    session <- sessionOption match {
      case Some(_) => itemCallback.add(req.userId, req.body, req.state)
      case None => Future.failed(Exceptions.userNotFound)
    }
  } yield session

}
