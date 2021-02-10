package lexi.ir.phases

import lexi.Phase
import lexi.ir.nodes.IrNode

object Proofs extends Phase:
  def apply(ir: IrNode): IrNode = ir
