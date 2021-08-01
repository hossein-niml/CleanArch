package contract.service

import contract.callback.todo._

abstract class Service[Request, Response] {
  val rep: ItemCallback

  def call(req: Request): Response
}