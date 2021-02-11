package lexi.backends.asm.ir

import lexi.ir.nodes.{IrClass, IrNode}
import org.objectweb.asm.{ClassWriter, Opcodes}

object IrClassVisitor extends IrVisitor[ClassWriter] {
  override def visit(ir: IrNode): ClassWriter =
    (((_: IrClass) => new ClassWriter(ClassWriter.COMPUTE_MAXS))
      andThen ((writer) => {
        writer.visit(
          Opcodes.V1_8,
          Opcodes.ACC_PUBLIC,
          ir.asInstanceOf[IrClass].name.get,
          null,
          "java/lang/Object",
          null
        )
        ir.asInstanceOf[IrClass]
          .methods
          .get
          .foreach(IrMethodVisitor(writer).visit(_))
        writer.visitEnd()
        writer
      }))(ir.asInstanceOf[IrClass])
}