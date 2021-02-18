package lexi.frontends.kotlin.phases

import lexi.{Phase, Source}
import lexi.ir.nodes.IrNode

object LanguageAnalysis extends Phase {
  def apply(source: Source): IrNode =
    ((SyntaxAnalysis(_))
      andThen (SemanticAnalysis(_))
      andThen (Ir(_)))(source)
}
