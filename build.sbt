name := "CleanArch"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.slf4j" % "slf4j-api" % "1.7.32",
  "org.slf4j" % "slf4j-simple" % "1.7.32",
  "org.scalameta" %% "munit" % "0.7.27" % Test)

testFrameworks += new TestFramework("munit.Framework")
