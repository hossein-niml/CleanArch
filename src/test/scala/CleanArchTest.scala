import modules.config._
import contract.service.todo._
import usecase.todo._

class CleanArchTest extends munit.FunSuite {
  def showItems(config: Config, idRange: Range): Unit = {
    val ids = idRange.toVector
    for {
      id <- ids
    } println(config.getItem(id).show)
    println("##########################")
  }

  val myConfig: Config = Config.ManualConfig

  myConfig.addItem(body = "this is my first item", state = false)
  myConfig.addItem(body = "2nd item", state = false)
  myConfig.addItem(body = "third item :D", state = false)

  showItems(myConfig, idRange = 1 to 3)

  myConfig.editBody(id = 1, newBody = "this is NEWWW first item")
  myConfig.editState(id = 2, newState = true)
  myConfig.editState(id = 3, newState = true)

  showItems(myConfig, idRange = 1 to 3)

  myConfig.editState(id = 3, newState = false)

  showItems(myConfig, idRange = 1 to 3)
}
