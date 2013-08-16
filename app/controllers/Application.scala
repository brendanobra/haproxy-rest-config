package controllers

import model.RouteMap
import  com.typesafe.scalalogging.slf4j.Logging //This gives better support for slf4j interface than play's Logger.
import play.api.mvc._
import play.api.libs.json.Json
import scala.collection.mutable


object Application extends Controller with Logging {
  val mappedRoutes: RouteMap=new RouteMap()
  mappedRoutes.map.addBinding(987,"anItem")
  mappedRoutes.map.addBinding(654,"another")


  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def json = Action {
    logger.info("json method called Standard Logger ");
    logger.info("try {} and {}","this","that")

    Ok(HaProxy.foo)
  }


  def routes(port: Int)=Action {
    val proxies=mappedRoutes.routes(port)
    Ok(views.html.showRoutes(port,proxies))

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

  def list()= Action  {

    var ports= mappedRoutes.map.keySet.toSet[Int];
    if(ports.size==0) {
      ports=Set(1234,5678)
    }
    Ok(views.html.listPorts(ports))
  }


}