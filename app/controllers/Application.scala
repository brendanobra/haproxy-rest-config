package controllers

import model.RouteMap
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import scala.collection.mutable
import org.slf4j.LoggerFactory

object Application extends Controller {
  val mappedRoutes: RouteMap=new RouteMap()
  mappedRoutes.map.addBinding(987,"anItem")
  mappedRoutes.map.addBinding(654,"another")
  val log: org.slf4j.Logger=LoggerFactory.getLogger(this.getClass);


  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def json = Action {
    Logger.info("json method called Standard Logger ");
    log.info("json method called slf4j 1={}, 2={}",Array[Object]("pram1","param2"))
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