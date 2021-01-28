package lexi.backend.asm.ir

import lexi.ir.nodes.{IrClass, IrNode, IrVisitor}
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

    val method = writer.visitMethod(
      Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
      "main",
      "([Ljava/lang/String;)V",
      null,
      null
    )
    method.visitCode()
    method.visitFieldInsn(
      Opcodes.GETSTATIC,
      "java/lang/System",
      "out",
      "Ljava/io/PrintStream;"
    )
    method.visitLdcInsn("Class compiled!")
    method.visitMethodInsn(
      Opcodes.INVOKEVIRTUAL,
      "java/io/PrintStream",
      "println",
      "(Ljava/lang/String;)V",
      false
    )
    method.visitInsn(Opcodes.RETURN)
    method.visitMaxs(0, 0)
    method.visitEnd()

    writer.visitEnd()
    writer
  }
}
