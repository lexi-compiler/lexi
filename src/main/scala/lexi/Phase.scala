package lexi

import lexi.ir.nodes.IrNode
import lexi.ir.phases.IrAnalysis

trait Phase

object Phase {
  val languageAnalysis: String => IrNode = (source: String) =>
    (lexi.frontend.kotlin.phases.Phase.languageAnalysis)(source)

  val irAnalysis: IrNode => IrNode = (ir: IrNode) => IrAnalysis(ir)
}
