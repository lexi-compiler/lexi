package lexi.backends.asm.ir

import lexi.ir.nodes.IrTree

trait IrVisitor[T] {
  def visit(ir: IrTree): T
}
