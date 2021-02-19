package lexi.backends.asm.ir

import lexi.ir.IrTree

trait IrVisitor[T] {
  def visit(ir: IrTree): T
}
