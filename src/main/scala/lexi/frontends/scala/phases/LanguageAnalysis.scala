package lexi.frontends.scala.phases

import lexi.frontends.scala.ir.ScalaFileVisitor
import lexi.ir.IrTree
import lexi.{Phase, Source}

import java.io.File
import scala.meta._

import fastparse._, fastparse.ScalaWhitespace

object LanguageAnalysis extends Phase {
  def apply(source: Source): IrTree = {
    val result = parse(source.text, scalaparse.Scala.CompilationUnit(_))
    println(result)

    // val ast = source.text.parse[scala.meta.Source].get
    // val ir = ScalaFileVisitor.visit(ast)
    // ir
    new IrTree {}
  }
}
