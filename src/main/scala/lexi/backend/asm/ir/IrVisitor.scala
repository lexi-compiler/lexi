package lexi.backend.asm.ir

import lexi.ir.nodes.IrNode

trait IrVisitor[T] {
  def visit(ir: IrNode): T
}
