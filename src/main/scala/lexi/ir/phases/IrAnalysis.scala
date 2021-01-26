package lexi.ir.phases

import lexi.ir.nodes.IrNode
import lexi.ir.phases.Phase.proofs

object IrAnalysis {
  def apply(ir: IrNode): IrNode =
    proofs(ir)
}
