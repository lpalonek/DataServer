package pl.palonek.server

import akka.http.scaladsl.server.Directives._
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
  * Created by lpalonek on 03/01/16.
  */
class ServerRoute {
  val logger = Logger(LoggerFactory.getLogger("ServerRoute"))
  val route =
    path("files") {
      get {
        complete {
          val fileReader = new FileReader
          logger.info("Files downloaded")
          fileReader.readFilesAndConvertThemToJson
        }
      }
    } ~
  path("data"){
    post{
      complete{
        // TODO
        ""
      }
    }
  }
}
