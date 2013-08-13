package controllers
import play.api.libs.json.Json
import model.RouteMap
import play.api.mvc._
import play.api._


object HaProxy  {
  def foo = {
    Json.obj("status" -> "ok", "name" -> "baz")
  }
}