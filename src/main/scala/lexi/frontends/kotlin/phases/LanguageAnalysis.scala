package lexi.frontends.kotlin.phases

import lexi.{Phase, Source, Tree}

object LanguageAnalysis extends Phase {
  def apply(source: Source): Tree =
    ((SyntaxAnalysis(_))
      andThen (SemanticAnalysis(_))
      andThen (Ir(_)))(source)
}
