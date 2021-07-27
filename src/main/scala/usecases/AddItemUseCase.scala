package usecases

import repositories._

class AddItemUseCase(addItemRep: Callback.AddItemCallback) extends Service.AddItemService {
  override val rep: Callback.AddItemCallback = addItemRep
  override def addItem(body: String, state: Boolean): Unit = {
    rep.addItem(body, state)
  }
}

object AddItemUseCase {
  def apply(rep: Callback.AddItemCallback): AddItemUseCase = {
    new AddItemUseCase(rep)
  }
}