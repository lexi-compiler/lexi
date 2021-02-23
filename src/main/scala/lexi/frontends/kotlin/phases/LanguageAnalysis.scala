package lexi.frontends.kotlin.phases

import lexi.ir.IrTree
import lexi.{Phase, Source}

object LanguageAnalysis extends Phase {
  def apply(source: Source): IrTree =
    ((SyntaxAnalysis(_))
      andThen (SemanticAnalysis(_))
      andThen (Ir(_)))(source)
}
