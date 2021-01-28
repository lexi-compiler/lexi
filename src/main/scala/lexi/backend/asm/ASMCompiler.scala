package lexi.backend.asm

import lexi.backend.asm.ir.IrClassVisitor.visit
import lexi.ir.nodes.IrClass

object ASMCompiler {
  val asm: (IrClass) => Array[Byte] = { irClass =>
    visit(irClass).toByteArray
  }
}
