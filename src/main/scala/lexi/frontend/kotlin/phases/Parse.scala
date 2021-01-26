package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.ast.ASTNode
import lexi.frontend.kotlin.phases.Phase.{
  lexicalAnalysis,
  semanticAnalysis,
  syntaxAnalysis
}

object Parse {
  def apply(source: String): ASTNode =
    (lexicalAnalysis
      andThen syntaxAnalysis
      andThen semanticAnalysis)(source)
}
