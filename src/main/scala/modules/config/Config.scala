package modules.config

import contract.callback.todo._
import contract.service.todo._
import modules.database._
import repository.todo._
import usecase.todo._
import domain.todo._

sealed abstract class Config {
  val dataBase: DataBase
  val rep: ItemCallback
  val addItemService: AddItemService
  val editStateService: EditStateService
  val editBodyService: EditBodyService
  val getItemService: GetItemService

  def addItem(body: String, state: Boolean): Unit = {
    addItemService.call(AddItemService.Request(body, state))
  }

  def editState(id: Int, newState: Boolean): Unit = {
    editStateService.call(EditStateService.Request(id, newState))
  }

  def editBody(id: Int, newBody: String): Unit = {
    editBodyService.call(EditBodyService.Request(id, newBody))
  }

  def getItem(id: Int): Item = {
    getItemService.call(GetItemService.Request(id))
  }
}


object Config {
  class ManualConfig extends Config {
    override val dataBase: DataBase = DataBase.myDataBase
    override val rep: ItemCallback = ItemRepository(dataBase)
    override val addItemService: AddItemService = AddItemUseCase(rep)
    override val editStateService: EditStateService = EditStateUseCase(rep)
    override val editBodyService: EditBodyService = EditBodyUseCase(rep)
    override val getItemService: GetItemService = GetItemUseCase(rep)
  }

  object ManualConfig extends ManualConfig
}