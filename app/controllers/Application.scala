package controllers

import model.RouteMap
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import scala.collection.mutable

object Application extends Controller {
  val mappedRoutes: RouteMap=new RouteMap()
  mappedRoutes.map.addBinding(1,"anItem")

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def json = Action {
    Ok(HaProxy.foo)
  }


  def routes(id: Int): mutable.Set[String] = {
    mappedRoutes.routes(id)

  }

  def addRoute(id: Int) = Action { request =>
    val body: AnyContent = request.body
    val textBody: Option[String] = body.asText

    // Expecting text body
    textBody.map {

      text => {
        mappedRoutes.map.addBinding(id,text)
        Ok("Added: id:"+id+" txt:" + text)
      }

    }.getOrElse {
      BadRequest("Expecting text/plain request body")
    }

  }

  def list(): Set[Int] = {
    mappedRoutes.map.keySet.toSet[Int];
  }


}