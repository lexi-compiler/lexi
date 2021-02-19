package lexi.frontends.scala.phases

import lexi.frontends.scala.ir.ScalaFileVisitor
import lexi.ir.nodes._
import lexi.{Phase, Source}

import java.io.File
import scala.meta._

object LanguageAnalysis extends Phase {
  def apply(source: Source): IrTree = {
    val ast = source.text.parse[scala.meta.Source].get
    val ir = ScalaFileVisitor.visit(ast)
    ir
  }
}
