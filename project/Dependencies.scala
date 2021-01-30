import sbt._

object Dependencies {
  lazy val asm = "org.ow2.asm" % "asm" % "9.0"
  lazy val antlr4 = "org.antlr" % "antlr4" % "4.9"
  lazy val scala3Compiler = "org.scala-lang" %% "scala3-compiler" % "3.0.0-M3"
  lazy val tastyInspector = "org.scala-lang" %% "scala3-tasty-inspector" % "3.0.0-M3"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.4-M1"
}
