package lexi

import lexi.ir.nodes.IrNode
import lexi.ir.phases.IrAnalysis

case class Compiler(
  val options: CompilerOptions
) {
  def run(source: String) =
    this.options.language.map(lang => Frontend(lang)(source))
}
