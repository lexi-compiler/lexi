package lexi.frontends.kotlin.phases

import lexi.frontends.kotlin.AST
import lexi.Phase

object TypeAnalysis extends Phase {
  def apply(ast: AST): AST =
    TypeInference(ast)
}
