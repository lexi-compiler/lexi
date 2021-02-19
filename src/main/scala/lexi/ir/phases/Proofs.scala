package lexi.ir.phases

import lexi.Phase
import lexi.ir.nodes.IrTree

object Proofs extends Phase {
  def apply(ir: IrTree): IrTree = ir
}
