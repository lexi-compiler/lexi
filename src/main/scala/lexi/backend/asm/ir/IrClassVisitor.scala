package lexi.backend.asm.ir

import lexi.ir.nodes.{IrClass, IrNode}
import org.objectweb.asm.{ClassWriter, Opcodes}

object IrClassVisitor extends IrVisitor[ClassWriter] {
  override def visit(ir: IrNode): ClassWriter = {
    val irClass = ir.asInstanceOf[IrClass]

    val writer = new ClassWriter(ClassWriter.COMPUTE_MAXS)

    writer.visit(
      Opcodes.V1_8,
      Opcodes.ACC_PUBLIC,
      irClass.name,
      null,
      "java/lang/Object",
      null
    )

    irClass.methods.foreach(IrMethodVisitor(writer).visit(_))

    writer.visitEnd()
    writer
  }
}
