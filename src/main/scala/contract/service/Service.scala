package contract.service

import scala.concurrent.ExecutionContext
import scala.concurrent.Future


abstract class Service[Request, Response] {

  def call(req: Request)(implicit ec: ExecutionContext): Future[Response]

}
