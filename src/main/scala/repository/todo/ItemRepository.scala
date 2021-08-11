package repository.todo

import contract.callback.todo._
import modules.database._
import domain.todo._
import modules.exceptions.Exceptions

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class ItemRepository(dataBase: DataBase[Map[Int, Item]]) extends ItemCallback {

  implicit val ec: ExecutionContext = DataBase.ec

  override def add(userId: Int, body: String, state: Boolean): Future[Map[Int, Item]] = {
    @tailrec
    def findNewId(map: Map[Int, Item], start: Int): Int = {
      if(map.contains(start)) findNewId(map, start + 1) else start
    }
    val newItem = Item(body, state)
    val prevItems = dataBase.get(userId).getOrElse(Map.empty)
    val newItemId = findNewId(map = prevItems, start = 1)
    dataBase.update(userId, prevItems + (newItemId -> newItem))
  }

  override def get(userId: Int): Future[Option[Map[Int, Item]]] = Future {
    dataBase.get(userId)
  }

  override def editBody(userId: Int, id: Int, newBody: String): Future[Map[Int, Item]] = {
    val userPrevItemsOption = dataBase.get(userId)
    userPrevItemsOption match {
      case None => Future.failed(Exceptions.userNotFound)
      case Some(userPrevItems) =>
        val itemOption = userPrevItems.get(id)
        itemOption match {
          case None => Future.failed(Exceptions.itemNotFound)
          case Some(item) =>
            val updatedItem = item.setBody(newBody)
            val userUpdatedItems = userPrevItems + (id -> updatedItem)
            dataBase.update(userId, userUpdatedItems)
        }
    }
  }

  override def editState(userId: Int, id: Int, newState: Boolean): Future[Map[Int, Item]] = {
    val userPrevItemsOption = dataBase.get(userId)
    userPrevItemsOption match {
      case None => Future.failed(Exceptions.userNotFound)
      case Some(userPrevItems) =>
        val itemOption = userPrevItems.get(id)
        itemOption match {
          case None => Future.failed(Exceptions.itemNotFound)
          case Some(item) =>
            val updatedItem = item.setState(newState)
            val userUpdatedItems = userPrevItems + (id -> updatedItem)
            dataBase.update(userId, userUpdatedItems)
        }
    }
  }

}


object ItemRepository {

  def apply(dataBase: DataBase[Map[Int, Item]]): ItemRepository = {
    new ItemRepository(dataBase)
  }

}
