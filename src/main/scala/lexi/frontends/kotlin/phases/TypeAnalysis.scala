package lexi.frontends.kotlin.phases

import lexi.{Phase, Tree}

object TypeAnalysis extends Phase {
  def apply(tree: Tree): Tree =
    (TypeInference.apply _)(tree)
}
