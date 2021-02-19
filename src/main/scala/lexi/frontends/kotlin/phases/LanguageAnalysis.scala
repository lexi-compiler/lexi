package lexi.frontends.kotlin.phases

import lexi.{Phase, Source}
import lexi.ir.nodes.IrTree

object LanguageAnalysis extends Phase {
  def apply(source: Source): IrTree =
    ((SyntaxAnalysis(_))
      andThen (SemanticAnalysis(_))
      andThen (Ir(_)))(source)
}
