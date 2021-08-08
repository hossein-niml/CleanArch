name := "CleanArch"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq("org.scalameta" %% "munit" % "0.7.26" % Test,
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5")


testFrameworks += new TestFramework("munit.Framework")
