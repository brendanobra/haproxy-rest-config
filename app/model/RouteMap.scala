package model

import collection.mutable.{HashMap, MultiMap,Set}


class RouteMap  {
  val  map: MultiMap[Int,String]=new HashMap[Int, Set[String]] with MultiMap[Int, String]
  
  def routes(port: Int): Set[String] = {

    map.get(port).getOrElse(Set[String]())
  }

}