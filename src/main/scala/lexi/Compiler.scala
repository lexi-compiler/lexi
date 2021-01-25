package lexi

import lexi.Phase._
import lexi.ir.nodes.IrNode

object Compiler {
  def compile(source: String): IrNode =
    (languageAnalysis
      andThen irAnalysis)(source)
}
