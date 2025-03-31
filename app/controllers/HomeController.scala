package controllers

import javax.inject.Inject
import play.api.mvc._

class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok("Welcome to the Todo App! Visit /todo to manage your todos.")
  }

  def health() = Action { implicit request: Request[AnyContent] =>
    Ok("OK")
  }
} 