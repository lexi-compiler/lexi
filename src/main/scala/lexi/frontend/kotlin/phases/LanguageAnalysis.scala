package lexi.frontend.kotlin.phases

import lexi.ir.nodes.IrNode

object LanguageAnalysis {
  def apply(source: String): IrNode = {
    (SyntaxAnalysis.apply _
      andThen SemanticAnalysis.apply
      andThen Ir.apply)(source)
  }
}
