package entities

class Item(_body: String, _state: Boolean) {
  private val body = _body
  private val state = _state
  def getBody(): String = body
  def getState(): Boolean = state
  def show(): String = body ++ " , " ++ state.toString
}

object Item {
  def apply(body: String, state: Boolean): Item = {
    new Item(body, state)
  }
}