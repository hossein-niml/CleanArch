package modules.config

import contract.callback.auth._
import contract.callback.todo._
import contract.service.todo._
import contract.service.auth._
import domain.auth._
import modules.database._
import repository.todo._
import repository.auth._
import usecase.todo._
import usecase.auth._
import domain.todo._


abstract class Config {

  val users: DataBase[User]

  val sessions: DataBase[Session]

  val items: DataBase[Map[Int, Item]]

  val itemRep: ItemCallback

  val userRep: UserCallback

  val addItemService: AddItemService

  val editStateService: EditStateService

  val editBodyService: EditBodyService

  val getItemService: GetItemService

  val signInService: SignInService

  val signOutService: SignOutService

  val signUpService: SignUpService

}


object Config {

  class ManualConfig extends Config {

    //DataBases
    override val users: DataBase[User] = new DataBase[User]

    override val sessions: DataBase[Session] = new DataBase[Session]

    override val items: DataBase[Map[Int, Item]] = new DataBase[Map[Int, Item]]

    //Repositories
    override val itemRep: ItemCallback = ItemRepository(items)

    override val userRep: UserCallback = UserRepository(users, sessions, items)

    //Services
    override val addItemService: AddItemService = new AddItemUseCase(itemRep, userRep)

    override val editStateService: EditStateService = new EditStateUseCase(itemRep, userRep)

    override val editBodyService: EditBodyService = new EditBodyUseCase(itemRep, userRep)

    override val getItemService: GetItemService = new GetItemUseCase(itemRep, userRep)

    override val signInService: SignInService = new SignInUseCase(userRep)

    override val signOutService: SignOutService = new SignOutUseCase(userRep)

    override val signUpService: SignUpService = new SignUpUseCase(userRep)
  }

  object ManualConfig extends ManualConfig
}
