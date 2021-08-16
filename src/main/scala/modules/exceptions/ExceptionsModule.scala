package modules.exceptions

object ExceptionsModule {

  val notFound: Exception = new Exception(s"Error: Not found")

  val userNotFound: Exception = new Exception(s"Error: Incorrect Username or Password")

  val itemNotFound: Exception = new Exception(s"Error: Item not found")

  val invalidUserName: Exception = new Exception(s"Error: Incorrect Username or Password")

  val invalidPassword: Exception = new Exception(s"Error: Incorrect Username or Password")

  def reSignIn(username: String): Exception = new Exception(s"Error: '$username' user has already signed in")

  def reSignUp(username: String): Exception = new Exception(s"Error: '$username' username has already taken")

  def reSignOut(userId: Long): Exception = new Exception(s"Error: User with id = $userId has already signed out")

}
