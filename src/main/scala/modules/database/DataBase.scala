package modules.database

import domain.auth._
import domain.todo._
import scala.annotation.tailrec

abstract class DataBase {
  def getItem(userID: Int, id: Int): Option[Item]

  def addItem(userID: Int, item: Item): Unit

  def editBody(userID: Int, id: Int, newBody: String): Unit

  def editState(userID: Int, id: Int, newState: Boolean): Unit

  def addUser(username: String, password: String): Unit

  def getUser(id: Int): Option[Session]

  def removeUser(id: Int): Unit

  def updateUser(user: User): Unit

  def signInUser(username: String, password: String): Unit

  def signOutUser(id: Int): Unit
}

object DataBase {

  class myDataBase extends DataBase {
    private var itemsMap: Map[Int, Map[Int, Item]] = Map.empty
    private var usersMap: Map[Int, Session] = Map.empty

    @tailrec
    private def findNewId(map: Map[Int, _], i: Int): Int = {
      if (map.contains(i)) findNewId(map, i + 1) else i
    }

    private def findUserIdByName(username: String): Int = {
      @tailrec
      def findUserIdByNameHelper(username: String, i: Int): Int = {
        val found = usersMap.get(i)
        found match {
          case Some(session) if session.user.username == username => i
          case _ => findUserIdByNameHelper(username, i + 1)
        }
      }

      findUserIdByNameHelper(username, 1)
    }

    override def getItem(userID: Int, id: Int): Option[Item] = {
      itemsMap.getOrElse(userID, throw new ClassNotFoundException()).get(id)
    }

    override def addItem(userID: Int, item: Item): Unit = {
      val newId = findNewId(itemsMap.getOrElse(userID, throw new ClassNotFoundException()), 1)
      val newItemsMap = itemsMap(userID) + (newId -> item)
      itemsMap = itemsMap.updated(userID, newItemsMap)
    }

    override def editBody(userID: Int, id: Int, newBody: String): Unit = {
      val prevItemOption = getItem(userID, id)
      prevItemOption match {
        case None => throw new ClassNotFoundException()
        case Some(prevItem) =>
          val newItemsMap = itemsMap(userID).-(id) + (id -> Item(newBody, prevItem.getState))
          itemsMap = itemsMap.updated(userID, newItemsMap)
      }
    }

    override def editState(userID: Int, id: Int, newState: Boolean): Unit = {
      val prevItemOption = getItem(userID, id)
      prevItemOption match {
        case None => throw new ClassNotFoundException()
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
    }

    override def getUser(id: Int): Option[Session] = {
      usersMap.get(id)
    }

    override def removeUser(id: Int): Unit = {
      usersMap = usersMap.-(id)
      itemsMap = itemsMap.-(id)
    }

    override def updateUser(user: User): Unit = {
      val userId = user.id
      val updatedSession = usersMap.getOrElse(userId, throw new ClassNotFoundException()).copy(user = user)
      usersMap = usersMap + (userId -> updatedSession)
    }

    override def signInUser(username: String, password: String): Unit = {
      val userId = findUserIdByName(username)
      val session = usersMap(userId)
      if (session.user.password != password) {
        throw new ClassNotFoundException()
      } else {
        val newSession = session.copy(isLogin = true)
        usersMap = usersMap + (userId -> newSession)
      }
    }

    override def signOutUser(id: Int): Unit = {
      val session = usersMap.getOrElse(id, throw new ClassNotFoundException())
      usersMap = usersMap + (id -> session.copy(isLogin = false))
    }
  }

  object myDataBase extends myDataBase

}