package lexi.backends.transformations

import lexi.Phase
import lexi.ir.{IrClass, IrFunction}
import org.objectweb.asm.{ClassWriter, MethodVisitor, Opcodes}

import scala.collection.mutable.ListBuffer

object ASM {
  def apply(ir: IrClass): Array[Byte] =
    asmClass(ir).toByteArray

  def asmClass(ir: IrClass): ClassWriter = {
    new ClassWriter(ClassWriter.COMPUTE_MAXS) {
      this.visit(
        Opcodes.V1_8,
        Opcodes.ACC_PUBLIC,
        ir.asInstanceOf[IrClass].name.get,
        null,
        "java/lang/Object",
        null
      )
      asmMethod(this)(ir)
      visitEnd()
    }
  }

  def asmMethod(
    classWriter: ClassWriter
  )(ir: IrFunction | IrClass): MethodVisitor | ListBuffer[MethodVisitor] = {
    val transformation: IrFunction => MethodVisitor = { ir =>
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

    ir match {
      case c: IrClass =>
        c.classBody.get.declarations
          .map(_.functionDeclaration)
          .filter(_ != None)
          .map(_.get)
          .map(transformation(_))
      case f: IrFunction => transformation(f)
    }
  }
}
