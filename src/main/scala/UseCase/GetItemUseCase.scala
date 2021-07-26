package UseCase

import Entities._
import Repositories._

class GetItemUseCase extends GetItemService {
  override def getItem(id: Int): Item = {
    GetItemRepository.getItem(id)
  }
}

object GetItemUseCase extends GetItemUseCase