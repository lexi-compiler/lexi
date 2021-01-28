package lexi.backend.asm

import lexi.backend.asm.ASMCompiler.asm
import lexi.ir.nodes.{IrClass, IrFunction, IrFunctionBody}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import java.nio.file.{Files, Path}

class ASMCompilerSpec extends AnyFunSpec with Matchers {
  describe("compile") {
    describe("IrClass") {
      val ir = IrClass("GeneratedClass")
      val bytecode = asm(ir)
      val file = s"${ir.name}.class"
      Files.write(Path.of(file), bytecode)
      it("writes a java class file") {
        Files.exists(Path.of(file)) shouldBe true
      }
    }

    ignore("IrFunction") {
      it("writes a function to a generated class file") {
        val ir = IrFunction("hello", "String", IrFunctionBody(Vector("yo")))
        ASMCompiler.compile(ir)
      }
    }
  }
}
