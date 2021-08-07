package repository.todo

import contract.callback.todo._
import modules.database.DataBase
import domain.todo._
import modules.exceptions.Exceptions
import scala.util.Try

class ItemRepository(dataBase: DataBase[Map[Int, Item]]) extends ItemCallback {

  override def add(userId: Int, body: String, state: Boolean): Try[Unit] = Try {
    val newItem = Item(body, state)
    val userPrevItems = dataBase.get(userId).getOrElse(Map.empty)
    val newItemId = dataBase.findNewId(userPrevItems)
    dataBase.update(userId, userPrevItems + (newItemId -> newItem))
  }

  override def get(userId: Int): Try[Map[Int, Item]] = Try {
    dataBase.get(userId).getOrElse(throw Exceptions.itemNotFound)
  }

  override def editBody(userId: Int, id: Int, newBody: String): Try[Map[Int, Item]] = Try {
    val userPrevItems = dataBase.get(userId).getOrElse(throw Exceptions.userNotFound)
    val updatedItem = userPrevItems.getOrElse(id, throw Exceptions.itemNotFound).setBody(newBody)
    val userUpdatedItems = userPrevItems + (id -> updatedItem)
    dataBase.update(userId, userUpdatedItems).getOrElse(throw Exceptions.userNotFound)
  }

  override def editState(userId: Int, id: Int, newState: Boolean): Try[Map[Int, Item]] = Try {
    val userPrevItems = dataBase.get(userId).getOrElse(throw Exceptions.userNotFound)
    val updatedItem = userPrevItems.getOrElse(id, throw Exceptions.itemNotFound).setState(newState)
    val userUpdatedItems = userPrevItems + (id -> updatedItem)
    dataBase.update(userId, userUpdatedItems).getOrElse(throw Exceptions.userNotFound)
  }

}

object ItemRepository {
  def apply(dataBase: DataBase[Map[Int, Item]]): ItemRepository = {
    new ItemRepository(dataBase)
  }
}
