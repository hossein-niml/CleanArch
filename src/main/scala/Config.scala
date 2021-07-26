import repositories._
import datebase._
import usecases._

class Config(_dataBase: DB) {
  private val dataBase = _dataBase
  private val addItemRep: AddItemRepository = AddItemRepository(dataBase)
  private val getItemRep: GetItemRepository = GetItemRepository(dataBase)
  private val editItemRep: EditItemRepository = EditItemRepository(dataBase)

  def addItemUseCase(): AddItemUseCase = AddItemUseCase(addItemRep)

  def editItemUseCase(): EditItemUseCase = EditItemUseCase(editItemRep)

  def getItemUseCase(): GetItemUseCase = GetItemUseCase(getItemRep)

}

object Config {
  def apply(dataBase: DB): Config = {
    new Config(dataBase)
  }
}