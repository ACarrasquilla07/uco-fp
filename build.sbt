lazy val root = (project in file(".")).settings(
  organization := "uco.fp",
  scalaVersion := "2.12.3",
  name := "uco-fp",
  version := "1.0.0",
  fork in run := true,
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats" % "0.9.0",
    "org.scalatest" %% "scalatest" % "3.0.3"
  )
)
scalafmtOnCompile in ThisBuild := true
