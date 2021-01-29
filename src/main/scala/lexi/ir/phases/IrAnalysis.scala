package lexi.ir.phases

import lexi.ir.nodes.IrNode

object IrAnalysis:
  def apply(ir: IrNode): IrNode =
    Proofs(ir)
