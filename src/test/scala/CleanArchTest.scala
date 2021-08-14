import contract.service.auth._
import contract.service.todo._
import domain.todo.Item
import modules.config._

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import modules.database._
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.ExecutionContext

class CleanArchTest extends munit.FunSuite {

  val logger: Logger = LoggerFactory.getLogger("logger")

  val toDo: Config = Config.ManualConfig

  implicit val ec: ExecutionContext = DataBase.ec

  def getAllItems(toDo: Config, userId: Int, itemsIdRange: Range): Vector[Item] = {
    val ids = itemsIdRange.toVector
    for {
      id <- ids
    } yield Await.result(toDo.getItemService.call(GetItemService.Request(userId, id)), Duration("1 seconds"))
  }

  def showItems(toDo: Config, userId: Int, itemsIdRange: Range): Unit = {
    val items = getAllItems(toDo, userId, itemsIdRange)
    for {
      item <- items
    } this.logger.info(item.toString)
    this.logger.info("##########################")
  }

  Await.result(toDo.signUpService.call(SignUpService.Request("hossein", "123456")), Duration("2 seconds"))

  Await.result(toDo.signUpService.call(SignUpService.Request("ali", "000")), Duration("2 seconds"))

  Await.result(toDo.signInService.call(SignInService.Request("hossein", "123456")), Duration("2 seconds"))

  Await.result(toDo.addItemService.call(AddItemService.Request(1, "hossein's first item", state = false)), Duration("2 seconds"))

  Await.result(toDo.addItemService.call(AddItemService.Request(1, "hossein's second item", state = false)), Duration("2 seconds"))

  Await.result(toDo.addItemService.call(AddItemService.Request(1, "hossein's third item :D", state = false)), Duration("2 seconds"))

  showItems(toDo, userId = 1, itemsIdRange = 1 to 3)

  Await.result(toDo.signInService.call(SignInService.Request("ali", "000")), Duration("2 seconds"))

  Await.result(toDo.addItemService.call(AddItemService.Request(2, "ali's first item", state = false)), Duration("2 seconds"))

  Await.result(toDo.addItemService.call(AddItemService.Request(2, "ali's second item", state = false)), Duration("2 seconds"))

  showItems(toDo, userId = 2, itemsIdRange = 1 to 2)

  Await.result(toDo.editStateService.call(EditStateService.Request(userId = 1, id = 1, newState = true)), Duration("2 seconds"))

  Await.result(toDo.editBodyService.call(EditBodyService.Request(userId = 1, id = 2, newBody = "hossein is CHANGING second item")), Duration("2 seconds"))

  showItems(toDo, userId = 1, itemsIdRange = 1 to 3)

  Await.result(toDo.signOutService.call(SignOutService.Request(userID = 1)), Duration("2 seconds"))

  Await.result(toDo.signInService.call(SignInService.Request("hossein", "123456")), Duration("2 seconds"))

  Await.result(toDo.addItemService.call(AddItemService.Request(1, "hossein's NEWWW item", state = false)), Duration("2 seconds"))

  showItems(toDo, userId = 1, itemsIdRange = 1 to 4)

}
