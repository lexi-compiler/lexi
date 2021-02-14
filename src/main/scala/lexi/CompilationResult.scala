package lexi

import lexi.ir.nodes.IrNode

case class CompilationResult(
  source: Source,
  ir: IrNode
)
