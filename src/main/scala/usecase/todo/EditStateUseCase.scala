package usecase.todo

import contract.callback.auth._
import contract.callback.todo._
import contract.service.todo._
import domain.todo.Item
import modules.exceptions.Exceptions

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class EditStateUseCase(itemCallback: ItemCallback, sessionCallback: SessionCallback) extends EditStateService {

  override def call(req: EditStateService.Request)(implicit ec: ExecutionContext): Future[Map[Int, Item]] = for {
    sessionOption <- sessionCallback.getById(req.userId)
    session <- sessionOption match {
      case Some(_) => itemCallback.editState(req.userId, req.id, req.newState)
      case None => Future.failed(Exceptions.userNotFound)
    }
  } yield session

}
