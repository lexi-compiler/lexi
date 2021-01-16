package lexi.frontends.kotlin

import lexi.frontends.kotlin.phases.{Ir, Parse}
import lexi.ir.IrNode

object Parser {
  def apply(source: String): IrNode =
    Ir(Parse(source))
}
