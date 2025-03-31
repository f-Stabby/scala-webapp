package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json.Json

@Singleton
class HealthController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def check() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.obj("status" -> "healthy"))
  }
} 