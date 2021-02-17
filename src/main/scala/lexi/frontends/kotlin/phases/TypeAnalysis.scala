package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.ast.Tree

object TypeAnalysis extends Phase {
  def apply(ast: Tree): Tree =
    (TypeInference.apply _) (ast)
}
