import Dependencies._

ThisBuild / scalaVersion := "3.0.0-M3"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.lexi-compiler"
ThisBuild / organizationName := "lexi"

lazy val root = (project in file("."))
  .enablePlugins(GraalVMNativeImagePlugin)
  .settings(
    name := "lexi",
    libraryDependencies ++= Seq(
      antlr4,
      asm,
      scala3Compiler,
      tastyInspector,
      munit % Test
    ),
    testFrameworks += new TestFramework("munit.Framework"),
    Compile / mainClass := Some("lexi.CLI"),
    graalVMNativeImageOptions ++= Seq(
      "--no-fallback",
      "--report-unsupported-elements-at-runtime"
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
