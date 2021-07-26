package UseCase

import Entities._
import Repositories._

class AddItemUseCase extends AddItemService {
  override def addItem(item: Item): Unit = {
    AddItemRepository.addItem(item)
  }
}

object AddItemUseCase extends AddItemUseCase