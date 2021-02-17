package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.ast.Tree

object SemanticAnalysis extends Phase:
  def apply(ast: Tree): Tree =
    TypeAnalysis(ast)
