import sbt._

object Dependencies {
  lazy val asm = "org.ow2.asm" % "asm" % "9.0"
  lazy val antlr4 = "org.antlr" % "antlr4" % "4.9"
  lazy val dottyCompiler = "ch.epfl.lamp" % "dotty-compiler_0.28" % "0.28.0-bin-20201014-ca67e4d-NIGHTLY"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.4-M1"
}
