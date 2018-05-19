name := """play-slick-jquery-data-table"""


organization := "com.techmonad"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play-slick" % "3.0.0",
  "com.h2database" % "h2" % "1.4.197",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
)
