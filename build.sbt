import sbt.Keys._

name := "DataServer"

version := "1.0"

scalaVersion := "2.11.7"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.2"

libraryDependencies ++= {
  val akkaStreamV = "2.0.1"
  Seq(
    "com.typesafe.akka" %% "akka-http-core-experimental" % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamV,
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
  )
}

