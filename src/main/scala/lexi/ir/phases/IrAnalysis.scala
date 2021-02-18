package lexi.ir.phases

import lexi.Phase
import lexi.ir.nodes.IrNode

object IrAnalysis extends Phase {
  def apply(ir: IrNode): IrNode =
    Proofs(ir)
}
