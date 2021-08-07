package modules.exceptions

object Exceptions {
  val notFound: Exception = new Exception(s"Error: Not found")

  val userNotFound: Exception = new Exception(s"Error: User not found")

  val itemNotFound: Exception = new Exception(s"Error: Item not found")

  val invalidUserName: Exception = new Exception(s"Error: Username is not correct")

  val invalidPassword: Exception = new Exception(s"Error: Password is not correct")

  def reSignIn(username: String): Exception = new Exception(s"Error: '$username' user has already signed in")

  def reSignUp(username: String): Exception = new Exception(s"Error: '$username' username has already taken")

  def reSignOut(userId: Int): Exception = new Exception(s"Error: User with id = $userId has already signed out")
}
