package repository

import javax.inject.{Inject, Singleton}
import models.Employee
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.Future

@Singleton()
class EmployeeRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends EmployeeTable with HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  def insert(employee: Employee): Future[Int] = db.run {
    empTableQueryInc += employee
  }

  def insertAll(employees: List[Employee]): Future[Seq[Int]] = db.run {
    empTableQueryInc ++= employees
  }

  def update(employee: Employee): Future[Int] = db.run {
    empTableQuery.filter(_.id === employee.id).update(employee)
  }

  def delete(id: Int): Future[Int] = db.run {
    empTableQuery.filter(_.id === id).delete
  }

  def getAll(): Future[List[Employee]] = db.run {
    empTableQuery.to[List].result
  }

  def getById(empId: Int): Future[Option[Employee]] = db.run {
    empTableQuery.filter(_.id === empId).result.headOption
  }

  def ddl: Future[Unit] = db.run(empTableQuery.schema.create)

}

private[repository] trait EmployeeTable {
  self: HasDatabaseConfigProvider[JdbcProfile] =>

  import profile.api._

  lazy protected val empTableQuery = TableQuery[EmployeeTable]
  lazy protected val empTableQueryInc = empTableQuery returning empTableQuery.map(_.id)

  private[EmployeeTable] class EmployeeTable(tag: Tag) extends Table[Employee](tag, "employee") {
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    val name: Rep[String] = column[String]("first_name", O.SqlType("VARCHAR(200)"))
    val position: Rep[String] = column[String]("position")
    val office: Rep[String] = column[String]("office")
    val startDate: Rep[String] = column[String]("start_date")
    val salary = column[String]("salary")

    def * : ProvenShape[Employee] = (name, position, office, startDate, salary, id.?) <> (Employee.tupled, Employee.unapply)
  }

}

