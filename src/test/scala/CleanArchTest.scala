import contract.service.auth._
import contract.service.todo._
import domain.todo.Item
import modules.config._
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import modules.database._

import java.lang.Thread.sleep
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class CleanArchTest extends munit.FunSuite {

  implicit val ec: ExecutionContext = DataBase.ec

  val logger: Logger = LoggerFactory.getLogger("logger")

  val toDo: Config = Config.ManualConfig

  val WAIT_TIME: Int = 1000

  def getAllItems(toDo: Config, userId: Int, itemsIdRange: Range): Vector[Future[Item]] = {
    val ids = itemsIdRange.toVector
    val result = for {
      id <- ids
    } yield toDo.getItemService.call(GetItemService.Request(userId, id))
    sleep(WAIT_TIME)
    result
  }

  def showItems(toDo: Config, userId: Int, itemsIdRange: Range): Unit = {
    val items = getAllItems(toDo, userId, itemsIdRange)
    for {
      item <- items
    } this.logger.info(item.toString)
    this.logger.info("##########################")
  }

  toDo.signUpService.call(SignUpService.Request("hossein", "123456"))

  toDo.signUpService.call(SignUpService.Request("ali", "000"))

  sleep(WAIT_TIME)

  toDo.signInService.call(SignInService.Request("hossein", "123456"))

  sleep(WAIT_TIME)

  toDo.addItemService.call(AddItemService.Request(1, "hossein's first item", state = false))

  sleep(WAIT_TIME)

  toDo.addItemService.call(AddItemService.Request(1, "hossein's second item", state = false))

  sleep(WAIT_TIME)

  toDo.addItemService.call(AddItemService.Request(1, "hossein's third item :D", state = false))

  sleep(WAIT_TIME)

  showItems(toDo, userId = 1, itemsIdRange = 1 to 3)

  toDo.signInService.call(SignInService.Request("ali", "000"))

  sleep(WAIT_TIME)

  toDo.addItemService.call(AddItemService.Request(2, "ali's first item", state = false))

  sleep(WAIT_TIME)

  toDo.addItemService.call(AddItemService.Request(2, "ali's second item", state = false))

  sleep(WAIT_TIME)

  showItems(toDo, userId = 2, itemsIdRange = 1 to 2)

  toDo.editStateService.call(EditStateService.Request(userId = 1, id = 1, newState = true))

  toDo.editBodyService.call(EditBodyService.Request(userId = 1, id = 2, newBody = "hossein is CHANGING second item"))

  sleep(WAIT_TIME)

  showItems(toDo, userId = 1, itemsIdRange = 1 to 3)

  toDo.signOutService.call(SignOutService.Request(userID = 1))

  sleep(WAIT_TIME)

  toDo.signInService.call(SignInService.Request("hossein", "123456"))

  sleep(WAIT_TIME)

  toDo.addItemService.call(AddItemService.Request(1, "hossein's NEWWW item", state = false))

  sleep(WAIT_TIME)

  showItems(toDo, userId = 1, itemsIdRange = 1 to 4)

}
