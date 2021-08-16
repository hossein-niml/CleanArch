package usecase.todo

import contract.callback.auth._
import contract.callback.todo._
import contract.service.todo._
import domain.todo.Item
import modules.exceptions.ExceptionsModule

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class EditBodyUseCase(itemCallback: ItemCallback, sessionCallback: SessionCallback) extends EditBodyService {

  override def call(req: EditBodyService.Request)(implicit ec: ExecutionContext): Future[Map[Long, Item]] = for {
    sessionOption <- sessionCallback.getById(req.userId)
    session <- sessionOption match {
      case Some(_) => itemCallback.editBody(req.userId, req.id, req.newBody)
      case None => Future.failed(ExceptionsModule.userNotFound)
    }
  } yield session

}
