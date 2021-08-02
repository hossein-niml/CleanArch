package repository.auth

import contract.callback.auth._
import modules.database._
import domain.auth._

class UserRepository(dataStore: DataBase) extends UserCallback {
  override val dataBase: DataBase = dataStore

  override def add(username: String, password: String): Unit = {
    dataBase.addUser(username, password)
  }

  override def getById(id: Int): Option[Session] = {
    dataBase.getUserById(id)
  }

  override def getByName(username: String): Option[User] = {
    dataBase.getUserByName(username)
  }

  override def remove(id: Int): Unit = {
    dataBase.removeUser(id)
  }

  override def update(user: User): Unit = {
    dataBase.updateUser(user)
  }

  override def signIn(id: Int): Unit = {
    dataBase.signInUser(id)
  }

  override def signOut(id: Int): Unit = {
    dataBase.signOutUser(id)
  }
}

object UserRepository {
  def apply(dataStore: DataBase): UserRepository = {
    new UserRepository(dataStore)
  }
}
