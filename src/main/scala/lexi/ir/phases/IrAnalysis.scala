package lexi.ir.phases

import lexi.Phase
import lexi.ir.IrTree

object IrAnalysis extends Phase {
  def apply(ir: IrTree): IrTree =
    Proofs(ir)
}
