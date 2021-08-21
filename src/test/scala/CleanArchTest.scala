import contract.service.auth._
import contract.service.todo._
import modules.config._
import org.slf4j._
import ch.qos.logback.classic
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.core.util.StatusPrinter

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import scala.util.Try
import scala.util.Failure
import scala.util.Success

class CleanArchTest extends munit.FunSuite {

  implicit val ec: ExecutionContext = ExecutionContext fromExecutor Executors.newCachedThreadPool()

  val logger: Logger = LoggerFactory.getLogger(classOf[CleanArchTest])

  val toDo: ConfigModule = ConfigModule.ManualConfig

  val lc: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]

  for {

    _ <- toDo.signUpService.call(SignUpService.Request("hossein", "123456")).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.signUpService.call(SignUpService.Request("ali", "000")).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.signInService.call(SignInService.Request("hossein", "123456")).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.addItemService.call(AddItemService.Request(1, "hossein's first item", state = false)).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.addItemService.call(AddItemService.Request(1, "hossein's second item", state = false)).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.addItemService.call(AddItemService.Request(1, "hossein's third item :D", state = false)).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.signInService.call(SignInService.Request("ali", "000")).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.addItemService.call(AddItemService.Request(2, "ali's first item", state = false)).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.addItemService.call(AddItemService.Request(2, "ali's second item", state = false)).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.editStateService.call(EditStateService.Request(userId = 1, id = 1, newState = true)).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.editBodyService.call(EditBodyService.Request(userId = 1, id = 2, newBody = "hossein is CHANGING second item")).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.signOutService.call(SignOutService.Request(userID = 1)).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.signInService.call(SignInService.Request("hossein", "123456")).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

    _ <- toDo.addItemService.call(AddItemService.Request(1, "hossein's NEWWW item", state = false)).transform {
      case Success(value) => Try { logger.info(value.toString) }
      case Failure(exception) => Try { logger.error(exception.getMessage) }
    }

  } ()
}
