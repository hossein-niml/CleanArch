package usecases

import entities._
import repositories._

class AddItemUseCase(addItemRep: AddItemCallback) extends AddItemService {
  override val rep = addItemRep
  override def addItem(item: Item): Unit = {
    rep.addItem(item)
  }
}

object AddItemUseCase {
  def apply(rep: AddItemCallback): AddItemUseCase = {
    new AddItemUseCase(rep)
  }
}