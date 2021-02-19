package lexi.ir.phases

import lexi.Phase
import lexi.ir.nodes.IrTree

object IrAnalysis extends Phase {
  def apply(ir: IrTree): IrTree =
    Proofs(ir)
}
