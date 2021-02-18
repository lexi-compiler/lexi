package lexi.frontends.kotlin.phases

import lexi.{Phase, Source}
import lexi.ir.nodes.IrNode

class Frontend extends Phase {
  def parse(source: Source): IrNode =
    ((SyntaxAnalysis(_))
      andThen (SemanticAnalysis(_))
      andThen (Ir(_)))(source)
}
