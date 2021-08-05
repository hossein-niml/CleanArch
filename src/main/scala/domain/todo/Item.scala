package domain.todo

case class Item(body: String, state: Boolean) {
  def setBody(body: String): Item = {
    copy(body = body)
  }

  def setState(state: Boolean): Item = {
    copy(state = state)
  }

  override def toString: String = s"$body , $state"
}
