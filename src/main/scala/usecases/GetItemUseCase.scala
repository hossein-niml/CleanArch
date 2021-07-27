package usecases

import entities._
import repositories._

class GetItemUseCase(getItemRep: Callback.GetItemCallback) extends Service.GetItemService {
  override val rep: Callback.GetItemCallback = getItemRep
  override def getItem(id: Int): Item = {
    rep.getItem(id)
  }
}

object GetItemUseCase {
  def apply(rep: Callback.GetItemCallback): GetItemUseCase = {
    new GetItemUseCase(rep)
  }
}