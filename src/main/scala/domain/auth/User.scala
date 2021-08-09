package domain.auth

case class User(id: Int, username: String, password: String) {

  def setUsername(newUsername: String): User = {
    copy(username = newUsername)
  }

  def setPassword(newPassword: String): User = {
    copy(password = newPassword)
  }

}
