import modules.config._

class CleanArchTest extends munit.FunSuite {
  def showItems(toDo: Config, userId: Int, itemsIdRange: Range): Unit = {
    val ids = itemsIdRange.toVector
    for {
      id <- ids
    } println(toDo.getItem(userId, id).get.show)
    println("##########################")
  }

  val toDo: Config = Config.ManualConfig

  //sign up 2 users
  toDo.signUp(username = "hossein", password = "123456") //userId = 1
  toDo.signUp(username = "ali", password = "000") //userId = 2

  toDo.signIn(username = "hossein", password = "123456") //hossein signed in

  //add items for hossein
  toDo.addItem(userId = 1, body = "hossein's first item", state = false)
  toDo.addItem(userId = 1, body = "hossein's second item", state = false)
  toDo.addItem(userId = 1, body = "hossein's third item :D", state = false)


  showItems(toDo, userId = 1, itemsIdRange = 1 to 3) //show hossein's items

  toDo.signIn(username = "ali", password = "000") //ali signed in

  //add items for ali
  toDo.addItem(userId = 2, body = "ali's first item", state = false)
  toDo.addItem(userId = 2, body = "ali's second item", state = false)

  showItems(toDo, userId = 2, itemsIdRange = 1 to 1) //show ali's items

  //edit hossein's items
  toDo.editState(userId = 1, id = 1, newState = true)
  toDo.editBody(userId = 1, id = 2, newBody = "hossein is CHANGING second item")

  showItems(toDo, userId = 1, itemsIdRange = 1 to 3) //show hossein's items

  toDo.signOut(id = 1) //hossein signed out
  toDo.signIn(username = "hossein", password = "123456") //hossein signed in again

  toDo.addItem(userId = 1, body = "hossein's new item", state = false)
  showItems(toDo, userId = 1, itemsIdRange = 1 to 4) //show hossein's items
}
