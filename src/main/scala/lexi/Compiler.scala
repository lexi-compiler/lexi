package lexi

import lexi.frontend.kotlin.phases.LanguageAnalysis
import lexi.ir.nodes.IrNode
import lexi.ir.phases.IrAnalysis

object Compiler:
  def compile(source: String): IrNode =
    (LanguageAnalysis.apply _
      andThen IrAnalysis.apply)(source)
