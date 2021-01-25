package lexi.frontend.kotlin.phases

import lexi.Phase
import lexi.frontend.kotlin.ast.ASTNode
import lexi.frontend.kotlin.phases.Phase.{
  lexicalAnalysis,
  semanticAnalysis,
  syntaxAnalysis
}

object Parse extends Phase {
  def apply(source: String): ASTNode =
    (lexicalAnalysis
      andThen syntaxAnalysis
      andThen semanticAnalysis)(source)
}
