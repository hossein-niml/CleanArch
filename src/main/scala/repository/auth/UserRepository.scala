package repository.auth

import contract.callback.auth._
import modules.database._
import domain.auth._

import scala.concurrent.Future

class UserRepository extends UserCallback with DataBase[User] {

  override def add(username: String, password: String): Future[Unit] = {
    val newId = lastNewId
    val user = User(newId, username, password)
    add(user)
  }

  override def getByName(username: String): Future[Option[User]] = Future {
    map.values.find(_.username == username)
  }

  override def remove(id: Long): Future[Unit] = {
    delete(id)
  }

  override def update(user: User): Future[User] = {
    update(user.id, user)
  }

}
