package usecases

import entities._
import repositories._

class AddItemUseCase(addItemRep: AddItemRepository) extends AddItemService {
  override val rep: AddItemRepository = addItemRep
  override def addItem(item: Item): Unit = {
    rep.addItem(item)
  }
}

object AddItemUseCase {
  def apply(rep: AddItemRepository): AddItemUseCase = {
    new AddItemUseCase(rep)
  }
}