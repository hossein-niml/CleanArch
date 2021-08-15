package domain.auth

case class Session(userId: Long, isLogin: Boolean) {

  def setLogin(login: Boolean): Session = {
    copy(isLogin = login)
  }

}
