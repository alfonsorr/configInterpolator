name := "config"
organization := "com.alfiler"
version := "0.1"

publishMavenStyle := true

scalaVersion := "2.12.4"

libraryDependencies ++= {
  val  contextualVersion = "1.1.0"
  val tsConfVersion = "1.3.1"
  Seq(
    "com.propensive" %% "contextual" % contextualVersion,
    "com.typesafe" % "config" % tsConfVersion,
    "org.scalatest" %% "scalatest" % "3.0.1" % Test
  )
}