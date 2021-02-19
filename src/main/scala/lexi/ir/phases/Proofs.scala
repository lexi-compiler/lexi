package lexi.ir.phases

import lexi.Phase
import lexi.ir.IrTree

object Proofs extends Phase {
  def apply(ir: IrTree): IrTree = ir
}
