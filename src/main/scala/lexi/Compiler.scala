package lexi

import lexi.ir.nodes.IrNode
import lexi.ir.phases.IrAnalysis

import scala.collection.mutable.ListBuffer

case class Compiler(
  var config: CompilerConfiguration = new CompilerConfiguration,
  var runs: ListBuffer[CompilationResult] = ListBuffer.empty
) {
  def run(sources: Vector[Source]) =
    runs.addOne(
      CompilationResult(
        configuration = config,
        sources = sources,
        ir = sources.map(source => Option(Frontend(source.language)(source.text)))
      )
    )
}
