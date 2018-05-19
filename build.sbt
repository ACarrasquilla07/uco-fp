lazy val root = (project in file(".")).settings(
  organization := "uco.fp",
  scalaVersion := "2.12.6",
  name := "uco-fp",
  version := "1.0.0",
  fork in run := true,
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % "1.1.0",
    "org.scalatest" %% "scalatest" % "3.0.3"
  )
)
scalafmtOnCompile in ThisBuild := true
