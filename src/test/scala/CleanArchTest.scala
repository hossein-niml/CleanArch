import contract.service.auth._
import contract.service.todo._
import domain.todo.Item
import modules.config._
import org.slf4j.LoggerFactory
import org.slf4j.Logger

import java.lang.Thread.sleep
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class CleanArchTest extends munit.FunSuite {

  implicit val ec: ExecutionContext = ExecutionContext fromExecutor Executors.newCachedThreadPool()

  val logger: Logger = LoggerFactory.getLogger(classOf[CleanArchTest])

  val toDo: ConfigModule = ConfigModule.ManualConfig

  val WAIT_TIME: Int = 100

  def getAllItems(toDo: ConfigModule, userId: Int, itemsIdRange: Range): Vector[Future[Item]] = {
    val ids = itemsIdRange.toVector
    val result = for {
      id <- ids
    } yield toDo.getItemService.call(GetItemService.Request(userId, id))
    sleep(WAIT_TIME)
    result
  }

  def showItems(toDo: ConfigModule, userId: Int, itemsIdRange: Range): Unit = {
    val items = getAllItems(toDo, userId, itemsIdRange)
    for {
      item <- items
    } this.logger.info(item.toString)
    this.logger.info("##########################")
  }

  for {
    _ <- toDo.signUpService.call(SignUpService.Request("hossein", "000"))
    _ <- toDo.addItemService.call(AddItemService.Request(1, "hossein's first item", state = false))
    _ <- toDo.signUpService.call(SignUpService.Request("ali", "000"))
    _ <- toDo.signInService.call(SignInService.Request("hossein", "123456"))
    _ <- toDo.addItemService.call(AddItemService.Request(1, "hossein's first item", state = false))
    _ <- toDo.addItemService.call(AddItemService.Request(1, "hossein's second item", state = false))
    _ <- toDo.addItemService.call(AddItemService.Request(1, "hossein's third item :D", state = false))
    _ <- toDo.signInService.call(SignInService.Request("ali", "000"))
    _ <- toDo.addItemService.call(AddItemService.Request(2, "ali's first item", state = false))
    _ <- toDo.addItemService.call(AddItemService.Request(2, "ali's second item", state = false))
    _ <- toDo.editStateService.call(EditStateService.Request(userId = 1, id = 1, newState = true))
    _ <- toDo.editBodyService.call(EditBodyService.Request(userId = 1, id = 2, newBody = "hossein is CHANGING second item"))
    _ <- toDo.signOutService.call(SignOutService.Request(userID = 1))
    _ <- toDo.signInService.call(SignInService.Request("hossein", "123456"))
    _ <- toDo.addItemService.call(AddItemService.Request(1, "hossein's NEWWW item", state = false))
  } ()

}
