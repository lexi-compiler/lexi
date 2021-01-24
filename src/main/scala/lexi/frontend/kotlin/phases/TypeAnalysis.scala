package lexi.frontend.kotlin.phases

import lexi.Phase
import lexi.frontend.kotlin.ast.ASTNode

object TypeAnalysis extends Phase {
  def apply(ast: ASTNode): ASTNode = {
    new ASTNode {}
  }
}
