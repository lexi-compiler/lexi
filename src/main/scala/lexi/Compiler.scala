package lexi

import lexi.ir.nodes.IrNode
import lexi.ir.phases.IrAnalysis

case class Compiler(
  config: CompilerConfiguration
) {
  def run: Vector[CompilationResult] =
    config.sources.map { source =>
      CompilationResult(
        source = source,
        ir = Frontend(source.language)(source.text)
      )
    }
}
