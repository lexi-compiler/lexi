package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.ast.ASTNode

object TypeAnalysis extends Phase:
  def apply(ast: ASTNode): ASTNode =
    (TypeInference.apply _)(ast)
