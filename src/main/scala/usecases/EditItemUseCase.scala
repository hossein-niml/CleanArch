package usecases

import repositories._

class EditItemUseCase(editItemRep: Callback.EditItemCallback) extends Service.EditItemService {
  override val rep: Callback.EditItemCallback = editItemRep
  override def editBody(id:Int, newMsg: String): Unit = {
    rep.editBody(id, newMsg)
  }

  override def editState(id: Int, newState: Boolean): Unit = {
    rep.editState(id, newState)
  }
}

object EditItemUseCase {
  def apply(rep: Callback.EditItemCallback): EditItemUseCase = {
    new EditItemUseCase(rep)
  }
}