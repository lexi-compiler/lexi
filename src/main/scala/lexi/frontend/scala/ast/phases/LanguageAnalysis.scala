package lexi.frontend.scala.ast.phases

import lexi.ir.nodes.IrNode
import dotty.tools._

object LanguageAnalysis:
  def apply(source: String): IrNode = {
    val compiler = new dotc.Compiler
    new IrNode {}
  }
