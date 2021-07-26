package usecases

import entities._
import repositories._

class GetItemUseCase(getItemRep: GetItemRepository) extends GetItemService {
  override val rep: GetItemRepository = getItemRep
  override def getItem(id: Int): Item = {
    rep.getItem(id)
  }
}

object GetItemUseCase {
  def apply(rep: GetItemRepository): GetItemUseCase = {
    new GetItemUseCase(rep)
  }
}