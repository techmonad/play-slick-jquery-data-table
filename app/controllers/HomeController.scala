package controllers

import javax.inject._
import models.PaginationData
import play.api._
import play.api.libs.json.Json
import play.api.mvc._
import repository.EmployeeRepository
import utils.JsonFormat._

import scala.concurrent.ExecutionContext

@Singleton
class HomeController @Inject()(empRepository: EmployeeRepository, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  val logger = Logger(this.getClass())

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }


  def search(): Action[AnyContent] = Action.async { request =>
    logger.info("Query" + request.queryString)
    logger.info("Request Params" + request.body.asFormUrlEncoded.get.keys)
    empRepository.getAll().map { res =>
      Ok(Json.toJson(PaginationData(1, res.length, res.length, res)))
    }

  }
}
