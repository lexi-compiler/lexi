package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.AST

object SemanticAnalysis extends Phase {
  def apply(ast: AST): AST =
    TypeAnalysis(ast)
}
