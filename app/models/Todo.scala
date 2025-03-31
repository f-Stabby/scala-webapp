package models

import slick.jdbc.PostgresProfile.api._

case class Todo(id: Option[Long], title: String, completed: Boolean = false)

class Todos(tag: Tag) extends Table[Todo](tag, "todos") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def title = column[String]("title")
  def completed = column[Boolean]("completed", O.Default(false))

  def * = (id.?, title, completed) <> (Todo.tupled, Todo.unapply)
} 