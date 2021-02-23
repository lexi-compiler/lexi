package lexi.backends.asm.ir

import lexi.Visitor
import lexi.ir.IrClass
import org.objectweb.asm.{ClassWriter, Opcodes}

object IrClassVisitor extends Visitor[IrClass, ClassWriter] {
  override def visit(ir: IrClass): ClassWriter = {
    new ClassWriter(ClassWriter.COMPUTE_MAXS) {
      this.visit(
        Opcodes.V1_8,
        Opcodes.ACC_PUBLIC,
        ir.asInstanceOf[IrClass].name.get,
        null,
        "java/lang/Object",
        null
      )
      ir.classBody.get.declarations.get
        .map(_.functionDeclaration)
        .filter(_ != None)
        .map(_.get)
        .foreach(IrMethodVisitor(this).visit(_))
      visitEnd()
    }
  }
}
