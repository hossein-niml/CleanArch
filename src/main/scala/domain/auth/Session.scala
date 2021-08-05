package domain.auth

case class Session(userId: Int, isLogin: Boolean) {
  def setLogin(login: Boolean): Session = {
    copy(isLogin = login)
  }
}