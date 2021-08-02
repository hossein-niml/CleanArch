package modules.config

import contract.callback.auth._
import contract.callback.todo._
import contract.service.todo._
import contract.service.auth._
import modules.database._
import repository.todo._
import repository.auth._
import usecase.todo._
import usecase.auth._
import domain.todo._


sealed abstract class Config {
  val dataBase: DataBase
  val itemRep: ItemCallback
  val userRep: UserCallback
  val addItemService: AddItemService
  val editStateService: EditStateService
  val editBodyService: EditBodyService
  val getItemService: GetItemService
  val signInService: SignInService
  val signOutService: SignOutService
  val signUpService: SignUpService

  def addItem(userId: Int, body: String, state: Boolean): Unit = {
    addItemService.call(AddItemService.Request(userId, body, state))
  }

  def editState(userId: Int, id: Int, newState: Boolean): Unit = {
    editStateService.call(EditStateService.Request(userId, id, newState))
  }

  def editBody(userId: Int, id: Int, newBody: String): Unit = {
    editBodyService.call(EditBodyService.Request(userId, id, newBody))
  }

  def getItem(userId: Int, id: Int): Option[Item] = {
    getItemService.call(GetItemService.Request(userId, id))
  }

  def signIn(username: String, password: String): Unit = {
    signInService.call(SignInService.Request(username, password))
  }

  def signUp(username: String, password: String): Unit = {
    signUpService.call(SignUpService.Request(username, password))
  }

  def signOut(id: Int): Unit = {
    signOutService.call(SignOutService.Request(id))
  }

}


object Config {
  class ManualConfig extends Config {
    override val dataBase: DataBase = DataBase.myDataBase
    override val itemRep: ItemCallback = ItemRepository(dataBase)
    override val userRep: UserCallback = UserRepository(dataBase)
    override val addItemService: AddItemService = AddItemUseCase(itemRep, userRep)
    override val editStateService: EditStateService = EditStateUseCase(itemRep, userRep)
    override val editBodyService: EditBodyService = EditBodyUseCase(itemRep, userRep)
    override val getItemService: GetItemService = GetItemUseCase(itemRep, userRep)
    override val signInService: SignInService = SignInUseCase(userRep)
    override val signOutService: SignOutService = SignOutUseCase(userRep)
    override val signUpService: SignUpService = SignUpUseCase(userRep)
  }

  object ManualConfig extends ManualConfig
}