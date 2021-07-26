import Entities._
import UseCase._

class CleanArchTest extends munit.FunSuite{

  AddItemUseCase.addItem(Item("this is my first item", false))
  AddItemUseCase.addItem(Item("2nd item", false))
  AddItemUseCase.addItem(Item("third item :D", false))

  println(GetItemUseCase.getItem(1))
  println(GetItemUseCase.getItem(2))
  println(GetItemUseCase.getItem(3))
  println("##########################")

  EditItemUseCase.editMsg(1, "this is NEWWW first item")
  EditItemUseCase.editState(2, true)
  EditItemUseCase.editState(3, true)

  println(GetItemUseCase.getItem(1))
  println(GetItemUseCase.getItem(2))
  println(GetItemUseCase.getItem(3))
  println("##########################")

  EditItemUseCase.editState(3, false)

  println(GetItemUseCase.getItem(1))
  println(GetItemUseCase.getItem(2))
  println(GetItemUseCase.getItem(3))
  println("##########################")
}
