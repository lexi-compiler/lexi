package lexi.frontend.kotlin.phases

import lexi.ir.nodes.IrNode

object LanguageAnalysis:
  def apply(source: String): IrNode =
    ((SyntaxAnalysis(_))
      andThen (SemanticAnalysis(_))
      andThen (Ir(_)))(source)
