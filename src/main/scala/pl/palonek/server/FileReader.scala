package pl.palonek.server

import java.io.File
import java.nio.file.{Files, Paths}
import java.util.Base64

import com.typesafe.config.ConfigFactory
import spray.json.DefaultJsonProtocol._
import spray.json._


/**
  * Created by lpalonek on 03/01/16.
  */
class FileReader {
  def readFilesAndConvertThemToJson = {
    val config = ConfigFactory.load()
    val dirPath = config.getString("files.path")
    val filesList = getListOfFiles(dirPath)
    convertFilesToJson(filesList)
  }

  private def convertFilesToJson(fileList: List[File]): String = {
    def convertFileListToImageFile = {
      fileList.map(file => JsObject("name" -> JsString(file.getName), "data" -> JsString(Base64
        .getEncoder
        .encodeToString(Files
          .readAllBytes(Paths
            .get(file
              .getAbsolutePath))))))
    }
    convertFileListToImageFile.toJson.toString
  }

  private def getListOfFiles(directory: String): List[File] = {
    val dir = new File(directory)
    if (dir.exists() && dir.isDirectory) {
      dir.listFiles().filter(file => file.isFile).toList
    } else {
      List[File]()
    }
  }
}
