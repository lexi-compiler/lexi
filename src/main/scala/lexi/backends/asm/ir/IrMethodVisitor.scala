package lexi.backends.asm.ir

import lexi.{Tree, Visitor}
import lexi.ir.{IrClass, IrFunction}
import org.objectweb.asm.{ClassWriter, MethodVisitor, Opcodes}

object IrMethodVisitor {
  def apply(classWriter: ClassWriter): IrMethodVisitor =
    new IrMethodVisitor(classWriter)
}

class IrMethodVisitor(classWriter: ClassWriter) extends Visitor[IrFunction, MethodVisitor] {
  override def visit(ir: IrFunction): MethodVisitor = {
    val method = classWriter.visitMethod(
      Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
      ir.name.get,
      s"([Ljava/lang/${ir.`type`};)V",
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
}
