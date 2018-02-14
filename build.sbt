name := """play-rest"""
organization := "TILoom6"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq( 
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "com.typesafe.play" %% "play-slick" % "3.0.3",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3",
  "org.postgresql" % "postgresql" % "42.2.1",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.3.0",
  "com.typesafe.slick" %% "slick-codegen" % "3.2.1",
  evolutions
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "TILoom6.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "TILoom6.binders._"
