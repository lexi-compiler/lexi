package lexi.frontend.kotlin

import lexi.frontend.Frontend
import lexi.frontend.kotlin.phases.Phase.{
  ir,
  lexicalAnalysis,
  semanticAnalysis,
  syntaxAnalysis
}
import lexi.ir.Nodes.IrNode

object KotlinFrontend extends Frontend {
  def apply(source: String): IrNode =
    (lexicalAnalysis
      andThen syntaxAnalysis
      andThen semanticAnalysis
      andThen ir)(source)
}
