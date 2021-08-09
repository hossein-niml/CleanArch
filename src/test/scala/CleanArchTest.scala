import contract.service.auth._
import contract.service.todo._
import modules.config._
import modules.exceptions.Exceptions
import scala.util.Try
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CleanArchTest extends munit.FunSuite {

  val logger: Logger = LoggerFactory.getLogger("logger")

  val toDo: Config = Config.ManualConfig

  def showItems(toDo: Config, userId: Int, itemsIdRange: Range): Try[Unit] = Try {
    val ids = itemsIdRange.toVector
    for {
      id <- ids
    } this.logger.info(toDo.getItemService.call(GetItemService.Request(userId, id)).getOrElse(throw Exceptions.itemNotFound).toString)

    this.logger.info("##########################")
  }

  def doThis(jobs: Vector[Try[Any]]): Unit = {
    val failedJobs = jobs.filter(job => job.isFailure)
    for {
      job <- failedJobs
    } this.logger.error(job.toString)
  }

  doThis(Vector(
    //sign up 2 users
    toDo.signUpService.call(SignUpService.Request("hossein", "123456")), //userId = 1
    toDo.signUpService.call(SignUpService.Request("ali", "000")), //userId = 2

    toDo.signInService.call(SignInService.Request("hossein", "123456")), //hossein signed in

    //add items for hossein
    toDo.addItemService.call(AddItemService.Request(1, "hossein's first item", state = false)),
    toDo.addItemService.call(AddItemService.Request(1, "hossein's second item", state = false)),
    toDo.addItemService.call(AddItemService.Request(1, "hossein's third item :D", state = false)),

    showItems(toDo, userId = 1, itemsIdRange = 1 to 3), //show hossein's items

    toDo.signInService.call(SignInService.Request("ali", "000")), //ali signed in

    //add items for ali
    toDo.addItemService.call(AddItemService.Request(2, "ali's first item", state = false)),
    toDo.addItemService.call(AddItemService.Request(2, "ali's second item", state = false)),

    showItems(toDo, userId = 2, itemsIdRange = 1 to 2), //show ali's items

    //edit hossein's items
    toDo.editStateService.call(EditStateService.Request(userId = 1, id = 1, newState = true)),
    toDo.editBodyService.call(EditBodyService.Request(userId = 1, id = 2, newBody = "hossein is CHANGING second item")),

    showItems(toDo, userId = 1, itemsIdRange = 1 to 3), //show hossein's items


    toDo.signOutService.call(SignOutService.Request(userID = 1)), //hossein signed out
    toDo.signInService.call(SignInService.Request("hossein", "123456")), //hossein signed in again


    toDo.addItemService.call(AddItemService.Request(1, "hossein's NEWWW item", state = false)),
    showItems(toDo, userId = 1, itemsIdRange = 1 to 4), //show hossein's items
  ))

}
