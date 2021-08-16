package modules.config

import contract.callback.auth._
import contract.callback.todo._
import contract.service.todo._
import contract.service.auth._
import repository.todo._
import repository.auth._
import usecase.todo._
import usecase.auth._

abstract class ConfigModule {

  //Repositories

  val itemRep: ItemCallback

  val userRep: UserCallback

  val sessionRep: SessionCallback

  //Services

  val addItemService: AddItemService

  val editStateService: EditStateService

  val editBodyService: EditBodyService

  val getItemService: GetItemService

  val signInService: SignInService

  val signOutService: SignOutService

  val signUpService: SignUpService

}


object ConfigModule {

  class ManualConfig extends ConfigModule {

    //Repositories

    override val itemRep: ItemCallback = new ItemRepository

    override val userRep: UserCallback = new UserRepository

    override val sessionRep: SessionCallback = new SessionRepository

    //Services

    override val addItemService: AddItemService = new AddItemUseCase(itemRep, sessionRep)

    override val editStateService: EditStateService = new EditStateUseCase(itemRep, sessionRep)

    override val editBodyService: EditBodyService = new EditBodyUseCase(itemRep, sessionRep)

    override val getItemService: GetItemService = new GetItemUseCase(itemRep, sessionRep)

    override val signInService: SignInService = new SignInUseCase(userRep, sessionRep)

    override val signOutService: SignOutService = new SignOutUseCase(sessionRep)

    override val signUpService: SignUpService = new SignUpUseCase(userRep, sessionRep)

  }

  object ManualConfig extends ManualConfig

}
