package lexi.backends.asm.phases

import lexi.Phase
import lexi.backends.asm.ir.IrClassVisitor.visit
import lexi.ir.IrClass

object ASM extends Phase {
  def apply(irClass: IrClass): Array[Byte] =
    visit(irClass).toByteArray
}
