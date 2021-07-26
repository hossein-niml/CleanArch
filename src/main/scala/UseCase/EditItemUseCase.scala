package UseCase

import Entities._
import Repositories._

class EditItemUseCase extends EditItemService {
  override def editMsg(id:Int, newMsg: String): Unit = {
    EditItemRepository.editMsg(id, newMsg)
  }

  override def editState(id: Int, newState: Boolean): Unit = {
    EditItemRepository.editState(id, newState)
  }
}

object EditItemUseCase extends EditItemUseCase