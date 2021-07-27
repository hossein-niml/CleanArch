package entities

class Item(itemBody: String, itemState: Boolean) {
  private val body = itemBody
  private val state = itemState
  def getBody(): String = body
  def getState(): Boolean = state
  def show(): String = body ++ " , " ++ state.toString
}

object Item {
  def apply(body: String, state: Boolean): Item = {
    new Item(body, state)
  }
}