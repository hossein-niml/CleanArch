package config

import datebase._
import repositories._
import usecases._

class Config() {

  import Config._

  def addItemUseCase(): Service.AddItemService = AddItemUseCase(addItemRep)

  def editItemUseCase(): Service.EditItemService = EditItemUseCase(editItemRep)

  def getItemUseCase(): Service.GetItemService = GetItemUseCase(getItemRep)

}

object Config {
  def apply(): Config = {
    new Config()
  }

  private val dataBase = DataBase.myDataBase
  private val addItemRep: Callback.AddItemCallback = AddItemRepository(dataBase)
  private val getItemRep: Callback.GetItemCallback = GetItemRepository(dataBase)
  private val editItemRep: Callback.EditItemCallback = EditItemRepository(dataBase)
}