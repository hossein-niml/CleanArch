package usecases

import entities._
import repositories._

class GetItemUseCase(getItemRep: GetItemCallback) extends GetItemService {
  override val rep = getItemRep
  override def getItem(id: Int): Item = {
    rep.getItem(id)
  }
}

object GetItemUseCase {
  def apply(rep: GetItemCallback): GetItemUseCase = {
    new GetItemUseCase(rep)
  }
}