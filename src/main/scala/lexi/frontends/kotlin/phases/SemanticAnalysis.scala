package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.ast.ASTNode

object SemanticAnalysis extends Phase:
  def apply(ast: ASTNode): ASTNode =
    TypeAnalysis(ast)
