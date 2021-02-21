package lexi.frontends.kotlin.phases

import lexi.{Phase, Tree}

object SemanticAnalysis extends Phase {
  def apply(tree: Tree): Tree =
    TypeAnalysis(tree)
}
