package lexi.backends.asm

import lexi.backends.asm.phases.ASM
import lexi.ir.nodes.{IrClass, IrFunction}
import org.objectweb.asm.ClassReader

class ASMSpec extends munit.FunSuite {
  test("generates JVM class from IrClass") {
    val irClass = IrClass(
      name = Some("GeneratedClass"),
      methods = Some(
        Vector(
          IrFunction(
            name = Some("main"),
            `type` = Some("String")
          )
        )
      )
    )
    val classFile = ASM(irClass)
    val classReader = new ClassReader(classFile)
    val className = classReader.getClassName
    assertEquals(className, "GeneratedClass")
  }
}
