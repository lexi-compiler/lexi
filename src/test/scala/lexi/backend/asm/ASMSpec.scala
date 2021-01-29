package lexi.backend.asm

import lexi.backend.asm.phases.ASM
import lexi.ir.nodes.{IrClass, IrFunction}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import java.nio.file.{Files, Path}

class ASMSpec extends AnyFunSpec with Matchers {
  describe("compile") {
    describe("IrClass") {
      val irClass = IrClass(
        name = "GeneratedClass",
        methods = Vector(IrFunction(name = "main", `type` = "String"))
      )
      val bytecode = ASM(irClass)
      val file = s"${irClass.name}.class"

      Files.write(Path.of(file), bytecode)

      it("writes a java class file") {
        Files.exists(Path.of(file)) shouldBe true
      }
    }
  }
}
