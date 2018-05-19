package models

case class Employee(
                     nameName: String,
                     lastName: String,
                     position: String,
                     office: String,
                     startDate: String,
                     salary: String,
                     id: Option[Int] = None
                   )

