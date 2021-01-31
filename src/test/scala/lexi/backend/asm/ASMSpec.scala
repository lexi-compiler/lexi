package lexi.backend.asm

import lexi.backend.asm.phases.ASM
import lexi.ir.nodes.{IrClass, IrFunction}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.objectweb.asm.ClassReader

import java.nio.file.{Files, Path}

class ASMSpec extends AnyFunSpec with Matchers:
  describe("compile") {
    describe("IrClass") {
      val irClass = IrClass(
        name = "GeneratedClass",
        methods = Vector(IrFunction(name = "main", `type` = "String"))
      )
      val classFile = ASM(irClass)
      val classReader = new ClassReader(classFile)
      val className = classReader.getClassName

      it("generates jvm bytecode") {
        className shouldBe "GeneratedClass"
      }
    }
  }
