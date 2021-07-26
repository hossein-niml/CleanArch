package usecases

import repositories._

class EditItemUseCase(editItemRep: EditItemRepository) extends EditItemService {
  override val rep: EditItemRepository = editItemRep
  override def editMsg(id:Int, newMsg: String): Unit = {
    rep.editMsg(id, newMsg)
  }

  override def editState(id: Int, newState: Boolean): Unit = {
    rep.editState(id, newState)
  }
}

object EditItemUseCase {
  def apply(rep: EditItemRepository): EditItemUseCase = {
    new EditItemUseCase(rep)
  }
}