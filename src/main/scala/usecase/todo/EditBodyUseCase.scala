package usecase.todo

import contract.callback.auth._
import contract.callback.todo._
import contract.service.todo._
import domain.todo.Item
import modules.exceptions.Exceptions

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class EditBodyUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends EditBodyService {

  override def call(req: EditBodyService.Request)(implicit ec: ExecutionContext): Future[Map[Int, Item]] = for {
    sessionOption <- userCallback.getSessionById(req.userId)
    session <- sessionOption match {
      case Some(_) => itemCallback.editBody(req.userId, req.id, req.newBody)
      case None => Future.failed(Exceptions.userNotFound)
    }
  } yield session

}
