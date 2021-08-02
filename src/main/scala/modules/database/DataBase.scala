package modules.database

import domain.auth._
import domain.todo._
import scala.annotation.tailrec
import modules.exceptions._

abstract class DataBase {
  def getItem(userID: Int, id: Int): Option[Item]

  def addItem(userID: Int, item: Item): Unit

  def editBody(userID: Int, id: Int, newBody: String): Unit

  def editState(userID: Int, id: Int, newState: Boolean): Unit

  def addUser(username: String, password: String): Unit

  def getUserById(id: Int): Option[Session]

  def getUserByName(username: String): Option[User]

  def removeUser(id: Int): Unit

  def updateUser(user: User): Unit

  def signInUser(id: Int): Unit

  def signOutUser(id: Int): Unit
}

object DataBase {

  class myDataBase extends DataBase {

    private var itemsMap: Map[Int, Map[Int, Item]] = Map.empty
    private var usersMap: Map[Int, Session] = Map.empty
    private var allUsers: Vector[User] = Vector.empty

    @tailrec
    private def findNewId(map: Map[Int, _], i: Int): Int = {
      if (map.contains(i)) findNewId(map, i + 1) else i
    }

    override def getItem(userID: Int, id: Int): Option[Item] = {
      itemsMap.getOrElse(userID, throw Exceptions.userNotFound).get(id)
    }

    override def addItem(userID: Int, item: Item): Unit = {
      val items = itemsMap.getOrElse(userID, throw Exceptions.userNotFound)
      val newId = findNewId(items, i = 1)
      val newItemsMap = itemsMap(userID) + (newId -> item)
      itemsMap = itemsMap.updated(userID, newItemsMap)
    }

    override def editBody(userID: Int, id: Int, newBody: String): Unit = {
      val prevItemOption = getItem(userID, id)
      prevItemOption match {
        case None => throw Exceptions.itemNotFound
        case Some(prevItem) =>
          val newItemsMap = itemsMap(userID).-(id) + (id -> Item(newBody, prevItem.getState))
          itemsMap = itemsMap.updated(userID, newItemsMap)
      }
    }

    override def editState(userID: Int, id: Int, newState: Boolean): Unit = {
      val prevItemOption = getItem(userID, id)
      prevItemOption match {
        case None => throw Exceptions.itemNotFound
        case Some(prevItem) =>
          val newItemsMap = itemsMap(userID).-(id) + (id -> Item(prevItem.getBody, newState))
          itemsMap = itemsMap.updated(userID, newItemsMap)
      }
    }

    override def addUser(username: String, password: String): Unit = {
      val newId = findNewId(usersMap, 1)
      val user = User(newId, username, password)
      usersMap = usersMap + (newId -> Session(user, isLogin = false))
      itemsMap = itemsMap + (newId -> Map.empty)
      allUsers = allUsers :+ user
    }

    override def getUserById(id: Int): Option[Session] = {
      usersMap.get(id)
    }

    override def getUserByName(username: String): Option[User] = {
      allUsers.find(_.username == username)
    }

    override def removeUser(id: Int): Unit = {
      usersMap = usersMap.-(id)
      itemsMap = itemsMap.-(id)
      allUsers = allUsers.filter(_.id != id)
    }

    override def updateUser(user: User): Unit = {
      val userId = user.id
      val items = itemsMap.getOrElse(userId, throw Exceptions.userNotFound)
      itemsMap = itemsMap + (userId -> items)
      val updatedSession = usersMap.getOrElse(userId, throw Exceptions.userNotFound).copy(user = user)
      usersMap = usersMap + (userId -> updatedSession)
      allUsers = allUsers.filter(_.id != userId) :+ user
    }

    override def signInUser(id: Int): Unit = {
      val prevSession = usersMap.getOrElse(id, throw Exceptions.userNotFound)
      usersMap = usersMap + (id -> prevSession.copy(isLogin = true))
    }

    override def signOutUser(id: Int): Unit = {
      val session = usersMap.getOrElse(id, throw Exceptions.userNotFound)
      usersMap = usersMap + (id -> session.copy(isLogin = false))
    }
  }

  object myDataBase extends myDataBase

}