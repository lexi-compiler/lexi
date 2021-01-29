package lexi

import lexi.ir.nodes.IrNode
import lexi.ir.phases.IrAnalysis

object Phase {
  val languageAnalysis: String => IrNode = (source: String) =>
    lexi.frontend.kotlin.phases.LanguageAnalysis(source)

  val irAnalysis: IrNode => IrNode = (ir: IrNode) => IrAnalysis(ir)
}
