package lexi.backend.asm.ir

import lexi.ir.nodes.{IrFunction, IrNode}
import org.objectweb.asm.{ClassWriter, MethodVisitor, Opcodes}

object IrMethodVisitor {
  def apply(classWriter: ClassWriter): IrMethodVisitor =
    new IrMethodVisitor(classWriter)
}

class IrMethodVisitor(classWriter: ClassWriter)
  extends IrVisitor[MethodVisitor] {
  override def visit(ir: IrNode): MethodVisitor =
    (
      (function: IrFunction) => {
        val method = classWriter.visitMethod(
          Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
          function.name,
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
        method
      }
    )(ir.asInstanceOf[IrFunction])
}
