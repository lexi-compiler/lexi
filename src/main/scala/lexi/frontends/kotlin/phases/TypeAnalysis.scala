package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.AST

object TypeAnalysis extends Phase {
  def apply(ast: AST): AST =
    (TypeInference.apply _)(ast)
}
