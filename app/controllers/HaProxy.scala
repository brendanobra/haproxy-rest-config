package controllers
import play.api.libs.json.Json

class HaProxy {
  def foo = {
    Json.obj("status" -> "ok", "name" -> "baz")
  }

}