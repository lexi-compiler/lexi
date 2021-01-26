package lexi.ir.phases

import lexi.ir.nodes.IrNode

object Phase {
  val proofs: IrNode => IrNode = (ir: IrNode) => Proofs(ir)
}
