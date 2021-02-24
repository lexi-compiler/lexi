package lexi.backends.asm

import lexi.backends.transformations.ASM
import lexi.ir.{IrClass, IrClassBody, IrDeclaration, IrFunction}
import org.objectweb.asm.ClassReader

class ASMSpec extends munit.FunSuite {
  test("generates JVM class from IrClass") {
    val irClass = IrClass(
      name = Some("GeneratedClass"),
      classBody = Some(
        IrClassBody(
          declarations = Vector(
            IrDeclaration(
              functionDeclaration = Some(
                IrFunction(
                  name = Some("main"),
                  `type` = Some("String")
                )
              )
            )
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
