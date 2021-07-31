import config._
import usecases._

class CleanArchTest extends munit.FunSuite {
  def showItems(getItemUseCase: Service.GetItemService, idRange: Range): Unit = {
    val ids = idRange.toVector
    for {
      id <- ids
    } println(getItemUseCase.getItem(id).show)
    println("##########################")
  }

  val myConfig: Config = Config.ManualConfig
  val addItemUseCase: Service.AddItemService = myConfig.getAddItemUseCase
  val editItemUseCase: Service.EditItemService = myConfig.getEditItemUseCase
  val getItemUseCase: Service.GetItemService = myConfig.getGetItemUseCase

  addItemUseCase.addItem(body = "this is my first item", state = false)
  addItemUseCase.addItem(body = "2nd item", state = false)
  addItemUseCase.addItem(body = "third item :D", state = false)

  showItems(getItemUseCase, idRange = 1 to 3)

  editItemUseCase.editBody(id = 1, newBody = "this is NEWWW first item")
  editItemUseCase.editState(id = 2, newState = true)
  editItemUseCase.editState(id = 3, newState = true)

  showItems(getItemUseCase, idRange = 1 to 3)

  editItemUseCase.editState(id = 3, newState = false)

  showItems(getItemUseCase, idRange = 1 to 3)
}
