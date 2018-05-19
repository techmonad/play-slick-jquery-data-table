package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import repository.EmployeeRepository
import utils.JsonFormat._
import play.api.libs.json.{JsError, JsValue, Json}

import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(empRepository: EmployeeRepository, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  val logger = Logger(this.getClass())

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }


  def search()= Action.async{ request =>
    empRepository.getAll().map { res =>
      logger.info("Emp list: " + res)
      //Ok(Json.toJson(res))
      println(request.body.asFormUrlEncoded)
      Ok("""{
           |  "draw": 2,
           |  "recordsTotal": 57,
           |  "recordsFiltered": 57,
           |  "data": [
           |    {
           |      "first_name": "Airi",
           |      "last_name": "Satou",
           |      "position": "Accountant",
           |      "office": "Tokyo",
           |      "start_date": "28th Nov 08",
           |      "salary": "$162,700"
           |    },
           |    {
           |      "first_name": "Angelica",
           |      "last_name": "Ramos",
           |      "position": "Chief Executive Officer (CEO)",
           |      "office": "London",
           |      "start_date": "9th Oct 09",
           |      "salary": "$1,200,000"
           |    },
           |    {
           |      "first_name": "Ashton",
           |      "last_name": "Cox",
           |      "position": "Junior Technical Author",
           |      "office": "San Francisco",
           |      "start_date": "12th Jan 09",
           |      "salary": "$86,000"
           |    },
           |    {
           |      "first_name": "Bradley",
           |      "last_name": "Greer",
           |      "position": "Software Engineer",
           |      "office": "London",
           |      "start_date": "13th Oct 12",
           |      "salary": "$132,000"
           |    },
           |    {
           |      "first_name": "Brenden",
           |      "last_name": "Wagner",
           |      "position": "Software Engineer",
           |      "office": "San Francisco",
           |      "start_date": "7th Jun 11",
           |      "salary": "$206,850"
           |    },
           |    {
           |      "first_name": "Brielle",
           |      "last_name": "Williamson",
           |      "position": "Integration Specialist",
           |      "office": "New York",
           |      "start_date": "2nd Dec 12",
           |      "salary": "$372,000"
           |    },
           |    {
           |      "first_name": "Bruno",
           |      "last_name": "Nash",
           |      "position": "Software Engineer",
           |      "office": "London",
           |      "start_date": "3rd May 11",
           |      "salary": "$163,500"
           |    },
           |    {
           |      "first_name": "Caesar",
           |      "last_name": "Vance",
           |      "position": "Pre-Sales Support",
           |      "office": "New York",
           |      "start_date": "12th Dec 11",
           |      "salary": "$106,450"
           |    },
           |    {
           |      "first_name": "Cara",
           |      "last_name": "Stevens",
           |      "position": "Sales Assistant",
           |      "office": "New York",
           |      "start_date": "6th Dec 11",
           |      "salary": "$145,600"
           |    },
           |    {
           |      "first_name": "Cedric",
           |      "last_name": "Kelly",
           |      "position": "Senior Javascript Developer",
           |      "office": "Edinburgh",
           |      "start_date": "29th Mar 12",
           |      "salary": "$433,060"
           |    }
           |  ]
           |}""".stripMargin)
    }

  }
}
