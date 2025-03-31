package controllers

import javax.inject.Inject
import play.api.mvc._
import repositories.TodoRepository
import play.api.libs.json._
import scala.concurrent.{ExecutionContext, Future}

class TodoController @Inject()(val controllerComponents: ControllerComponents, todoRepository: TodoRepository)(implicit ec: ExecutionContext) extends BaseController {
  
  implicit val todoFormat: OFormat[models.Todo] = Json.format[models.Todo]

  def index() = Action.async { implicit request: Request[AnyContent] =>
    todoRepository.list().map { todos =>
      Ok(views.html.todo.index(todos))
    }
  }

  def create() = Action(parse.formUrlEncoded) { implicit request =>
    val title = request.body.get("title").flatMap(_.headOption).getOrElse("")
    if (title.nonEmpty) {
      todoRepository.create(title)
    }
    Redirect(routes.TodoController.index())
  }

  def toggle(id: Long) = Action.async {
    todoRepository.toggle(id).map { _ =>
      Redirect(routes.TodoController.index())
    }
  }

  def delete(id: Long) = Action.async {
    todoRepository.delete(id).map { _ =>
      Redirect(routes.TodoController.index())
    }
  }
} 