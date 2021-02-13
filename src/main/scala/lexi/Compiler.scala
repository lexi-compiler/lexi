package lexi

import lexi.ir.nodes.IrNode
import lexi.ir.phases.IrAnalysis

case class Compiler(
  config: CompilerConfiguration
) {
  def run: Vector[IrNode] =
    config.sources.map(source => Frontend(source.language)(source.text))
}
