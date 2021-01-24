package lexi

import lexi.Phase._
import lexi.ir.IrNode

object Compiler {
  def compile(source: String): IrNode =
    (parse
      andThen analysis
      andThen ir)(source)
}
