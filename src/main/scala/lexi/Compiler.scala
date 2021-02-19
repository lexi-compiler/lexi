package lexi

import lexi.ir.nodes.IrTree
import lexi.ir.phases.IrAnalysis

case class Compiler(
  context: Context = new Context
) {
  def run(sources: Vector[Source]): Vector[CompilationUnit] =
    sources.map(CompilationUnit(_))
}
