package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.ast.ASTNode

object SemanticAnalysis {
  def apply(ast: ASTNode): ASTNode =
    TypeAnalysis(ast)
}
