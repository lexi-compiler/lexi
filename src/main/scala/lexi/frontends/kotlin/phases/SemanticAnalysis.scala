package lexi.frontends.kotlin.phases

import lexi.frontends.kotlin.AST
import lexi.Phase

object SemanticAnalysis extends Phase {
  def apply(ast: AST): AST =
    TypeAnalysis(ast)
}
