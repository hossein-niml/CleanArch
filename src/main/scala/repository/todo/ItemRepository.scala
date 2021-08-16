package repository.todo

import contract.callback.todo._
import modules.database._
import domain.todo._
import modules.exceptions.ExceptionsModule

import scala.annotation.tailrec
import scala.concurrent.Future

class ItemRepository extends ItemCallback with DataBaseModule[Map[Long, Item]] {

  override def add(userId: Long, body: String, state: Boolean): Future[Map[Long, Item]] = {
    @tailrec
    def findNewId(map: Map[Long, Item], start: Long): Long = {
      if(map.contains(start)) findNewId(map, start + 1) else start
    }
    val prevItems = get(userId).getOrElse(Map.empty)
    val newItemId = findNewId(map = prevItems, start = 1)
    val newItem = Item(newItemId, body, state)
    update(userId, prevItems + (newItemId -> newItem))
  }

  override def getById(userId: Long): Future[Option[Map[Long, Item]]] = Future {
    get(userId)
  }

  override def editBody(userId: Long, id: Long, newBody: String): Future[Map[Long, Item]] = {
    val userPrevItemsOption = get(userId)
    userPrevItemsOption match {
      case None => Future.failed(ExceptionsModule.userNotFound)
      case Some(userPrevItems) =>
        val itemOption = userPrevItems.get(id)
        itemOption match {
          case None => Future.failed(ExceptionsModule.itemNotFound)
          case Some(item) =>
            val updatedItem = item.setBody(newBody)
            val userUpdatedItems = userPrevItems + (id -> updatedItem)
            update(userId, userUpdatedItems)
        }
    }
  }

  override def editState(userId: Long, id: Long, newState: Boolean): Future[Map[Long, Item]] = {
    val userPrevItemsOption = get(userId)
    userPrevItemsOption match {
      case None => Future.failed(ExceptionsModule.userNotFound)
      case Some(userPrevItems) =>
        val itemOption = userPrevItems.get(id)
        itemOption match {
          case None => Future.failed(ExceptionsModule.itemNotFound)
          case Some(item) =>
            val updatedItem = item.setState(newState)
            val userUpdatedItems = userPrevItems + (id -> updatedItem)
            update(userId, userUpdatedItems)
        }
    }
  }

}
