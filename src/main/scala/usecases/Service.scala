package usecases

import entities._
import repositories.Callback

sealed abstract class Service {
  val rep: Callback
}

object Service {

  abstract class AddItemService extends Service {
    def addItem(body: String, state: Boolean): Unit
  }

  abstract class GetItemService extends Service {
    def getItem(id: Int): Item
  }

  abstract class EditItemService extends Service {
    def editBody(id: Int, newBody: String): Unit

    def editState(id: Int, newState: Boolean): Unit
  }

}