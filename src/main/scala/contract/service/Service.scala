package contract.service

import contract.callback.todo._

abstract class Service[Req, Res] {
  val rep: ItemCallback

  def call(req: Req): Res
}