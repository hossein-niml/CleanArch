package config

import datebase._
import repositories._
import usecases._


sealed abstract class Config {
  val dataBase: DataBase
  val addItemRep: Callback.AddItemCallback
  val getItemRep: Callback.GetItemCallback
  val editItemRep: Callback.EditItemCallback

  def getAddItemUseCase: Service.AddItemService

  def getEditItemUseCase: Service.EditItemService

  def getGetItemUseCase: Service.GetItemService
}




object Config {
  class ManualConfig extends Config {

    override val dataBase: DataBase = DataBase.myDataBase
    override val addItemRep: Callback.AddItemCallback = AddItemRepository(dataBase)
    override val getItemRep: Callback.GetItemCallback = GetItemRepository(dataBase)
    override val editItemRep: Callback.EditItemCallback = EditItemRepository(dataBase)

    override def getAddItemUseCase: Service.AddItemService = AddItemUseCase(addItemRep)

    override def getEditItemUseCase: Service.EditItemService = EditItemUseCase(editItemRep)

    override def getGetItemUseCase: Service.GetItemService = GetItemUseCase(getItemRep)

  }
  object ManualConfig extends ManualConfig
}