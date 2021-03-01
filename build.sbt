import Dependencies._

ThisBuild / scalaVersion := "3.0.0-M3"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.lexi-compiler"
ThisBuild / organizationName := "lexi"

lazy val root = (project in file("."))
//  .enablePlugins(GraalVMNativeImagePlugin)
  .enablePlugins(NativeImagePlugin)
  .settings(
    name := "lexi",
    libraryDependencies ++= Vector(
      antlr4,
      asm,
//      scala3Compiler,
//      tastyInspector,
      scalameta,
      munit % Test
    ),
    testFrameworks += new TestFramework("munit.Framework"),
    Compile / mainClass := Some("lexi.CLI"),
//    graalVMNativeImageOptions ++= {
//      val reflectionFile = Keys.sourceDirectory.in(Compile).value./("graal")./("reflection.json")
//      assert(reflectionFile.exists, "no such file: " + reflectionFile)
//      Vector(
//        "-H:+ReportUnsupportedElementsAtRuntime",
//        "-Dscalafmt.native-image=true",
//        "--initialize-at-build-time",
//        "--no-server",
//        "--enable-http",
//        "--enable-https",
//        "-H:EnableURLProtocols=http,https",
//        "--enable-all-security-services",
//        "--no-fallback",
//        s"-H:ReflectionConfigurationFiles=$reflectionFile",
//        "--allow-incomplete-classpath",
//        "-H:+ReportExceptionStackTraces"
//      )
//    }
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
