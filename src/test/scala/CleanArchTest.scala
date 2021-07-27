import entities._
import usecases._
import datebase._

class CleanArchTest extends munit.FunSuite{

  val myConfig = Config(myDataBase)
  val addItemUseCase = myConfig.addItemUseCase()
  val editItemUseCase = myConfig.editItemUseCase()
  val getItemUseCase = myConfig.getItemUseCase()

  addItemUseCase.addItem(Item("this is my first item", false))
  addItemUseCase.addItem(Item("2nd item", false))
  addItemUseCase.addItem(Item("third item :D", false))

  println(getItemUseCase.getItem(1).show())
  println(getItemUseCase.getItem(2).show())
  println(getItemUseCase.getItem(3).show())
  println("##########################")

  editItemUseCase.editMsg(1, "this is NEWWW first item")
  editItemUseCase.editState(2, true)
  editItemUseCase.editState(3, true)

  println(getItemUseCase.getItem(1).show())
  println(getItemUseCase.getItem(2).show())
  println(getItemUseCase.getItem(3).show())
  println("##########################")

  editItemUseCase.editState(3, false)

  println(getItemUseCase.getItem(1).show())
  println(getItemUseCase.getItem(2).show())
  println(getItemUseCase.getItem(3).show())
  println("##########################")
}
