package pl.palonek.server


import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory


/**
  * Created by lpalonek on 02/01/16.
  */
object Main extends App with Service {

  implicit val system = ActorSystem()
  override implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val config = ConfigFactory.load()
  override val logger = Logging(system, getClass)
  val bindingFuture = Http().bindAndHandle(route, config.getString("http.address"), config.getInt("http.port"))
}
