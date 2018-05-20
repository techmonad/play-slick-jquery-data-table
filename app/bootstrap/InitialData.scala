package bootstrap

import com.google.inject.Inject
import javax.inject.Singleton
import models.Employee
import play.Logger
import play.api.libs.json.Json
import repository.EmployeeRepository
import utils.JsonFormat._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}


@Singleton
class InitialData @Inject()(empRepository: EmployeeRepository)(implicit ec: ExecutionContext) {

  def insert: Future[Unit] = for {
   // emps <- empRepository.getAll() if (emps.length == 0)
    _ <- empRepository.insertAll(Data.employees)
  } yield {}

  try {
    Logger.info("DB initialization.................")
    Await.result(empRepository.ddl, Duration.Inf)
    Await.result(insert, Duration.Inf)
  } catch {
    case ex: Exception =>
      Logger.error("Error in database initialization ", ex)
  }

}

object Data {

  def employees: List[Employee] = Json.parse(empJson).as[List[Employee]]


  val empJson ="""[{"name":"Airi Satou","position":"Accountant","office":"Tokyo","startDate":"28th Nov 08","salary":"$162,700"},{"name":"Angelica Ramos","position":"Chief Executive Officer (CEO)","office":"London","startDate":"9th Oct 09","salary":"$1,200,000"},{"name":"Ashton Cox","position":"Junior Technical Author","office":"San Francisco","startDate":"12th Jan 09","salary":"$86,000"},{"name":"Bradley Greer","position":"Software Engineer","office":"London","startDate":"13th Oct 12","salary":"$132,000"},{"name":"Brenden Wagner","position":"Software Engineer","office":"San Francisco","startDate":"7th Jun 11","salary":"$206,850"},{"name":"Brielle Williamson","position":"Integration Specialist","office":"New York","startDate":"2nd Dec 12","salary":"$372,000"},{"name":"Bruno Nash","position":"Software Engineer","office":"London","startDate":"3rd May 11","salary":"$163,500"},{"name":"Caesar Vance","position":"Pre-Sales Support","office":"New York","startDate":"12th Dec 11","salary":"$106,450"},{"name":"Cara Stevens","position":"Sales Assistant","office":"New York","startDate":"6th Dec 11","salary":"$145,600"},{"name":"Cedric Kelly","position":"Senior Javascript Developer","office":"Edinburgh","startDate":"29th Mar 12","salary":"$433,060"},{"name":"Charde Marshall","position":"Regional Director","office":"San Francisco","startDate":"16th Oct 08","salary":"$470,600"},{"name":"Colleen Hurst","position":"Javascript Developer","office":"San Francisco","startDate":"15th Sep 09","salary":"$205,500"},{"name":"Dai Rios","position":"Personnel Lead","office":"Edinburgh","startDate":"26th Sep 12","salary":"$217,500"},{"name":"Donna Snider","position":"Customer Support","office":"New York","startDate":"25th Jan 11","salary":"$112,000"},{"name":"Doris Wilder","position":"Sales Assistant","office":"Sidney","startDate":"20th Sep 10","salary":"$85,600"},{"name":"Finn Camacho","position":"Support Engineer","office":"San Francisco","startDate":"7th Jul 09","salary":"$87,500"},{"name":"Fiona Green","position":"Chief Operating Officer (COO)","office":"San Francisco","startDate":"11th Mar 10","salary":"$850,000"},{"name":"Garrett Winters","position":"Accountant","office":"Tokyo","startDate":"25th Jul 11","salary":"$170,750"},{"name":"Gavin Joyce","position":"Developer","office":"Edinburgh","startDate":"22nd Dec 10","salary":"$92,575"},{"name":"Gavin Cortez","position":"Team Leader","office":"San Francisco","startDate":"26th Oct 08","salary":"$235,500"},{"name":"Gloria Little","position":"Systems Administrator","office":"New York","startDate":"10th Apr 09","salary":"$237,500"},{"name":"Haley Kennedy","position":"Senior Marketing Designer","office":"London","startDate":"18th Dec 12","salary":"$313,500"},{"name":"Hermione Butler","position":"Regional Director","office":"London","startDate":"21st Mar 11","salary":"$356,250"},{"name":"Herrod Chandler","position":"Sales Assistant","office":"San Francisco","startDate":"6th Aug 12","salary":"$137,500"},{"name":"Hope Fuentes","position":"Secretary","office":"San Francisco","startDate":"12th Feb 10","salary":"$109,850"},{"name":"Howard Hatfield","position":"Office Manager","office":"San Francisco","startDate":"16th Dec 08","salary":"$164,500"},{"name":"Jackson Bradshaw","position":"Director","office":"New York","startDate":"26th Sep 08","salary":"$645,750"},{"name":"Jena Gaines","position":"Office Manager","office":"London","startDate":"19th Dec 08","salary":"$90,560"},{"name":"Jenette Caldwell","position":"Development Lead","office":"New York","startDate":"3rd Sep 11","salary":"$345,000"},{"name":"Jennifer Chang","position":"Regional Director","office":"Singapore","startDate":"14th Nov 10","salary":"$357,650"},{"name":"Jennifer Acosta","position":"Junior Javascript Developer","office":"Edinburgh","startDate":"1st Feb 13","salary":"$75,650"},{"name":"Jonas Alexander","position":"Developer","office":"San Francisco","startDate":"14th Jul 10","salary":"$86,500"},{"name":"Lael Greer","position":"Systems Administrator","office":"London","startDate":"27th Feb 09","salary":"$103,500"},{"name":"Martena Mccray","position":"Post-Sales support","office":"Edinburgh","startDate":"9th Mar 11","salary":"$324,050"},{"name":"Michael Silva","position":"Marketing Designer","office":"London","startDate":"27th Nov 12","salary":"$198,500"},{"name":"Michael Bruce","position":"Javascript Developer","office":"Singapore","startDate":"27th Jun 11","salary":"$183,000"},{"name":"Michelle House","position":"Integration Specialist","office":"Sidney","startDate":"2nd Jun 11","salary":"$95,400"},{"name":"Olivia Liang","position":"Support Engineer","office":"Singapore","startDate":"3rd Feb 11","salary":"$234,500"},{"name":"Paul Byrd","position":"Chief Financial Officer (CFO)","office":"New York","startDate":"9th Jun 10","salary":"$725,000"},{"name":"Prescott Bartlett","position":"Technical Author","office":"London","startDate":"7th May 11","salary":"$145,000"},{"name":"Quinn Flynn","position":"Support Lead","office":"Edinburgh","startDate":"3rd Mar 13","salary":"$342,000"},{"name":"Rhona Davidson","position":"Integration Specialist","office":"Tokyo","startDate":"14th Oct 10","salary":"$327,900"},{"name":"Sakura Yamamoto","position":"Support Engineer","office":"Tokyo","startDate":"19th Aug 09","salary":"$139,575"},{"name":"Serge Baldwin","position":"Data Coordinator","office":"Singapore","startDate":"9th Apr 12","salary":"$138,575"},{"name":"Shad Decker","position":"Regional Director","office":"Edinburgh","startDate":"13th Nov 08","salary":"$183,000"},{"name":"Shou Itou","position":"Regional Marketing","office":"Tokyo","startDate":"14th Aug 11","salary":"$163,000"},{"name":"Sonya Frost","position":"Software Engineer","office":"Edinburgh","startDate":"13th Dec 08","salary":"$103,600"},{"name":"Suki Burks","position":"Developer","office":"London","startDate":"22nd Oct 09","salary":"$114,500"},{"name":"Tatyana Fitzpatrick","position":"Regional Director","office":"London","startDate":"17th Mar 10","salary":"$385,750"},{"name":"Thor Walton","position":"Developer","office":"New York","startDate":"11th Aug 13","salary":"$98,540"},{"name":"Tiger Nixon","position":"System Architect","office":"Edinburgh","startDate":"25th Apr 11","salary":"$320,800"},{"name":"Timothy Mooney","position":"Office Manager","office":"London","startDate":"11th Dec 08","salary":"$136,200"},{"name":"Unity Butler","position":"Marketing Designer","office":"San Francisco","startDate":"9th Dec 09","salary":"$85,675"},{"name":"Vivian Harrell","position":"Financial Controller","office":"San Francisco","startDate":"14th Feb 09","salary":"$452,500"},{"name":"Yuri Berry","position":"Chief Marketing Officer (CMO)","office":"New York","startDate":"25th Jun 09","salary":"$675,000"},{"name":"Zenaida Frank","position":"Software Engineer","office":"New York","startDate":"4th Jan 10","salary":"$125,250"},{"name":"Zorita Serrano","position":"Software Engineer","office":"San Francisco","startDate":"1st Jun 12","salary":"$115,000"}]"""


}