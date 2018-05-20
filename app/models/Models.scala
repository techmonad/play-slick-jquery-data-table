package models



case class Employee(
                     name:String,
                     position: String,
                     office: String,
                     startDate: String,
                     salary: String,
                     id: Option[Int] = None
                   )



case class PaginationData(draw: Int, recordsTotal: Int, recordsFiltered: Int, data: List[Employee])