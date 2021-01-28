package lexi.backend.asm

import lexi.backend.asm.ir.IrClassVisitor.visit
import lexi.ir.nodes.{IrClass, IrFunction}
import org.objectweb.asm._

import java.nio.file.{Files, Path}

object ASMCompiler {
  val asm: (IrClass) => Array[Byte] = { irClass =>
    visit(irClass).toByteArray
  }

  def compile(function: IrFunction): Unit = {
    val writer = new ClassWriter(ClassWriter.COMPUTE_MAXS)
    writer.visit(
      Opcodes.V1_8,
      Opcodes.ACC_PUBLIC,
      function.name.capitalize,
      null,
      "java/lang/Object",
      null
    )

    val method = writer.visitMethod(
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
    method.visitLdcInsn("Function compiled!")
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

    val bytes = writer.toByteArray
    Files.write(
      Path.of(s"/Users/mattmoore/Desktop/${function.name.capitalize}.class"),
      bytes
    )
  }
}
