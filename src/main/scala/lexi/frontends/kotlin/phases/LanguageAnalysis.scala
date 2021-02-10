package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.ir.nodes.IrNode

object LanguageAnalysis extends Phase:
  def apply(source: String): IrNode =
    ((SyntaxAnalysis(_))
      andThen (SemanticAnalysis(_))
      andThen (Ir(_)))(source)
