package lexi.backend.asm

import lexi.ir.{IrClass, IrFunction, IrFunctionBody}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ASMParserSpec extends AnyFunSpec with Matchers {
  describe("compile") {
    describe("IrClass") {
      it("writes a java class file") {
        val irTree = IrClass("GeneratedClass")
        ASMCompiler.compile(irTree)
      }
    }

    describe("IrFunction") {
      it("writes a function to a generated class file") {
        val irTree = IrFunction("hello", "String", IrFunctionBody("yo"))
        ASMCompiler.compile(irTree)
      }
    }
  }
}
