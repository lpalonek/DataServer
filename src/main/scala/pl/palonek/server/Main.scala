package pl.palonek.server


import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory


/**
  * Created by lpalonek on 02/01/16.
  */
object Main {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  def main(args: Array[String]) {
    val serverRoute = new ServerRoute
    val route = serverRoute.route
    val config = ConfigFactory.load()
    val bindingFuture = Http().bindAndHandle(route, config.getString("http.address"), config.getInt("http.port"))
  }
}
