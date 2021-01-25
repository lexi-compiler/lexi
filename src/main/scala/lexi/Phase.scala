package lexi

import lexi.frontend.kotlin.KotlinFrontend
import lexi.ir.nodes.IrNode
import lexi.ir.phases.IrAnalysis

trait Phase

object Phase {
  val languageAnalysis: String => IrNode = (source: String) =>
    KotlinFrontend(source)

  val irAnalysis: IrNode => IrNode = (ir: IrNode) => IrAnalysis(ir)
}
