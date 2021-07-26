name := "CleanArch"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies += "org.scalameta" %% "munit" % "0.7.26" % Test

testFrameworks += new TestFramework("munit.Framework")
