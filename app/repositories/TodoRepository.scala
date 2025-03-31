package repositories

import models.{Todo, Todos}
import slick.jdbc.PostgresProfile.api._
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.JdbcBackend

class TodoRepository @Inject()(db: JdbcBackend.Database)(implicit ec: ExecutionContext) {
  private val todos = TableQuery[Todos]

  def list(): Future[Seq[Todo]] = db.run(todos.result)

  def create(title: String): Future[Todo] = {
    val todo = Todo(None, title)
    db.run((todos returning todos.map(_.id)) += todo)
      .map(id => todo.copy(id = Some(id)))
  }

  def toggle(id: Long): Future[Int] = {
    db.run(todos.filter(_.id === id).map(_.completed).update(true))
  }

  def delete(id: Long): Future[Int] = {
    db.run(todos.filter(_.id === id).delete)
  }
} 