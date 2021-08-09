package contract.service


abstract class Service[Request, Response] {

  def call(req: Request): Response

}
