package repositories

import entities._
import datebase._

sealed abstract class Callback {
  val dataBase: DB
}

object Callback {

  abstract class AddItemCallback extends Callback {
    def addItem(body: String, state: Boolean): Unit
  }

  abstract class GetItemCallback extends Callback {
    def getItem(id: Int): Item
  }

  abstract class EditItemCallback extends Callback {
    def editBody(id: Int, newBody: String): Unit

    def editState(id: Int, newState: Boolean): Unit
  }

}