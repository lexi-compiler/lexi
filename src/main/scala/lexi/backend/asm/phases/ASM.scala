package lexi.backend.asm.phases

import lexi.backend.asm.ir.IrClassVisitor.visit
import lexi.ir.nodes.IrClass

object ASM {
  def apply(irClass: IrClass): Array[Byte] =
    ((visit(_: IrClass))
      andThen (_.toByteArray))(irClass)
}
