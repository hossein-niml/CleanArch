package modules.exceptions

object Exceptions {
  val userNotFound: Exception = new Exception(s"Error: User not found")

  val itemNotFound: Exception = new Exception(s"Error: Item not found")

  val invalidUserName: Exception = new Exception(s"Error: Username is not correct")

  val invalidPassword: Exception = new Exception(s"Error: Password is not correct")
}
