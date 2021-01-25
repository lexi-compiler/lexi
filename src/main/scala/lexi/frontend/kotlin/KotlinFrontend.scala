package lexi.frontend.kotlin

import lexi.frontend.Frontend
import lexi.frontend.kotlin.phases.Phase.{ir, parse}
import lexi.ir.nodes.IrNode

object KotlinFrontend extends Frontend {
  def apply(source: String): IrNode = {
    (parse
      andThen ir)(source)
  }
}
