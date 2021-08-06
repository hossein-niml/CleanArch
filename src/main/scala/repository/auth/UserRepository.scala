package repository.auth

import contract.callback.auth._
import modules.database._
import domain.auth._
import domain.todo.Item
import modules.exceptions.Exceptions
import scala.util.Try

class UserRepository(users: DataBase[User], sessions: DataBase[Session], items: DataBase[Map[Int, Item]]) extends UserCallback {

  override def add(username: String, password: String): Try[Unit] = Try {
    val newId = users.findNewId(users.map)
    val user = User(newId, username, password)
    val isLogin = false
    val session = Session(newId, isLogin)
    users.add(user)
    sessions.add(session)
    items.add(Map.empty)
  }

  override def getSessionById(id: Int): Try[Session] = Try {
    sessions.get(id).getOrElse(throw Exceptions.userNotFound)
  }

  override def getUserByName(username: String): Try[User] = Try {
    users.map.values.find(_.username == username).getOrElse(throw Exceptions.userNotFound)
  }

  override def remove(id: Int): Try[Unit] = Try {
    users.delete(id)
    sessions.delete(id)
    items.delete(id)
  }

  override def updateUser(user: User): Try[User] = Try {
    val id = user.id
    users.update(id, user)
  }

  override def updateSession(id: Int, session: Session):  Try[Session] = Try {
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
