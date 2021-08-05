package repository.auth

import contract.callback.auth._
import modules.database._
import domain.auth._
import domain.todo.Item
import modules.exceptions.Exceptions


class UserRepository(users: DataBase[User], sessions: DataBase[Session], items: DataBase[Map[Int, Item]]) extends UserCallback {

  override def add(username: String, password: String): Unit = {
    val newId = users.findNewId(users.map)
    val user = User(newId, username, password)
    val isLogin = false
    val session = Session(newId, isLogin)
    users.add(user)
    sessions.add(session)
    items.add(Map.empty)
  }

  override def getSessionById(id: Int): Option[Session] = {
    sessions.get(id)
  }

  override def getUserByName(username: String): Option[User] = {
    users.map.values.find(_.username == username)
  }

  override def remove(id: Int): Unit = {
    users.delete(id)
    sessions.delete(id)
    items.delete(id)
  }

  override def updateUser(user: User): User = {
    val id = user.id
    users.update(id, user)
  }

  override def updateSession(id: Int, session: Session): Session = {
    val prevSession = sessions.get(id)
    prevSession match {
      case None => throw Exceptions.userNotFound
      case Some(_) => sessions.update(id, session)
    }
  }
}

object UserRepository {
  def apply(users: DataBase[User], sessions: DataBase[Session], items: DataBase[Map[Int, Item]]): UserRepository = {
    new UserRepository(users, sessions, items)
  }
}
