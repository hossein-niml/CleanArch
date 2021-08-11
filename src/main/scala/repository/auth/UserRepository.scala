package repository.auth

import contract.callback.auth._
import modules.database._
import domain.auth._
import domain.todo.Item

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class UserRepository(users: DataBase[User], sessions: DataBase[Session], items: DataBase[Map[Int, Item]]) extends UserCallback {

  implicit val ec: ExecutionContext = DataBase.ec

  override def add(username: String, password: String): Future[Unit] = {
    val newId = users.lastNewId
    val user = User(newId, username, password)
    val isLogin = false
    val session = Session(newId, isLogin)
    users.add(user)
    sessions.add(session)
    items.add(Map.empty)
  }

  override def getSessionById(id: Int): Future[Option[Session]] = Future {
    sessions.get(id)
  }

  override def getUserByName(username: String): Future[Option[User]] = Future {
    users.map.values.find(_.username == username)
  }

  override def remove(id: Int): Future[Unit] = {
    users.delete(id)
    sessions.delete(id)
    items.delete(id)
  }

  override def updateUser(user: User): Future[User] = {
    users.update(user.id, user)
  }

  override def updateSession(id: Int, session: Session): Future[Session] = {
    sessions.update(id, session)
  }

}


object UserRepository {

  def apply(users: DataBase[User], sessions: DataBase[Session], items: DataBase[Map[Int, Item]]): UserRepository = {
    new UserRepository(users, sessions, items)
  }

}
