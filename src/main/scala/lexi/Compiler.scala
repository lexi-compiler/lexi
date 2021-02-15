package lexi

import lexi.ir.nodes.IrNode
import lexi.ir.phases.IrAnalysis

case class Compiler(
  var config: CompilerConfiguration = new CompilerConfiguration
) {
  def run(sources: Vector[Source]) =
    CompilationResult(
      configuration = config,
      sources = sources,
      ir = sources.map(source => Frontend(source))
    )
}
