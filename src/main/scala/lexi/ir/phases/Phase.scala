package lexi.ir.phases

import lexi.ir.Nodes.IrNode

trait Phase extends lexi.Phase

object Phase extends Phase {
  val proofs: IrNode => IrNode = (ir: IrNode) => Proofs(ir)
}
