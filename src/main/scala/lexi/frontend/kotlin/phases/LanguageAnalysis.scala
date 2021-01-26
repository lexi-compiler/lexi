package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.phases.Phase.{ir, parse}
import lexi.ir.nodes.IrNode

object LanguageAnalysis {
  def apply(source: String): IrNode = {
    (parse
      andThen ir)(source)
  }
}
