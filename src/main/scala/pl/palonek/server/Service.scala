package pl.palonek.server

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.HttpEntity.Strict
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute
import akka.stream.Materializer
import akka.util.ByteString

import scala.concurrent.ExecutionContextExecutor


/**
  * Created by lpalonek on 03/01/16.
  */
trait Service {

  implicit val system: ActorSystem

  implicit def executor: ExecutionContextExecutor

  implicit val materializer: Materializer

  val logger: LoggingAdapter

  def filesResponse: String = {
    val fileReader = new FileReader
    logger.info("Files downloaded")
    fileReader.readFilesAndConvertThemToJson
  }

  def completeWithJson(json: String): StandardRoute =
    complete{
      val byteString = ByteString.fromString(json)
      new Strict(ContentTypes.`application/json`, byteString)
    }

  val route =
    path("files") {
      get {
        completeWithJson {
          filesResponse
        }
      }
    } ~
      path("data") {
        post {
          complete {
            // TODO
            ""
          }
        }
      }
}
