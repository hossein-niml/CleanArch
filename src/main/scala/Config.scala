import repositories._
import datebase._
import usecases._

class Config(_dataBase: DB) {
  private val dataBase = _dataBase
  private val addItemRep: AddItemCallback = AddItemRepository(dataBase)
  private val getItemRep: GetItemCallback = GetItemRepository(dataBase)
  private val editItemRep: EditItemCallback = EditItemRepository(dataBase)

  def addItemUseCase(): AddItemService = AddItemUseCase(addItemRep)

  def editItemUseCase(): EditItemService = EditItemUseCase(editItemRep)

  def getItemUseCase(): GetItemService = GetItemUseCase(getItemRep)

}

object Config {
  def apply(dataBase: DB): Config = {
    new Config(dataBase)
  }
}