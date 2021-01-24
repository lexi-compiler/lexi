package lexi.ir.phases

import lexi.ir.Nodes.IrNode
import lexi.ir.phases.Phase.proofs

object IrAnalysis extends Phase {
  def apply(ir: IrNode): IrNode =
    proofs(ir)
}
