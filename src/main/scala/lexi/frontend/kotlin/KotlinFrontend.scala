package lexi.frontend.kotlin

import lexi.frontend.kotlin.phases.{Ir, Parser}
import lexi.ir.IrNode

object KotlinFrontend {
  def apply(source: String): IrNode =
    Ir(Parser.parse(Parser(source)))
}
