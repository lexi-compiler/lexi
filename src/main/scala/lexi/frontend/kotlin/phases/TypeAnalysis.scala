package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.ast.ASTNode

object TypeAnalysis:
  def apply(ast: ASTNode): ASTNode =
    TypeInference(ast)
