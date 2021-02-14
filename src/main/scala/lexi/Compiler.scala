package lexi

import lexi.ir.nodes.IrNode
import lexi.ir.phases.IrAnalysis

case class Compiler(
  config: CompilerConfiguration
) {
  def run: Vector[Source] =
    config.sources.map {
      source => source.copy(ir = Option(Frontend(source.language)(source.text)))
    }
}
