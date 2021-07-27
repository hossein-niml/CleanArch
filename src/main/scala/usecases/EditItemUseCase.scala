package usecases

import repositories._

class EditItemUseCase(editItemRep: EditItemCallback) extends EditItemService {
  override val rep = editItemRep
  override def editMsg(id:Int, newMsg: String): Unit = {
    rep.editMsg(id, newMsg)
  }

  override def editState(id: Int, newState: Boolean): Unit = {
    rep.editState(id, newState)
  }
}

object EditItemUseCase {
  def apply(rep: EditItemCallback): EditItemUseCase = {
    new EditItemUseCase(rep)
  }
}