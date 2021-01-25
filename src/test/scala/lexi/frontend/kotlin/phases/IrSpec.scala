package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.KotlinFrontend
import lexi.ir.Nodes.{IrExpression, IrFunction, IrFunctionBody}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class IrSpec extends AnyFunSpec with Matchers {
  describe("expression function without parameters") {
    val source = """fun hello(): String = "Hello Maki!""""
    val ir = KotlinFrontend(source)

    it("parses an IrFunction") {
      ir shouldBe IrFunction(
        name = "hello",
        `type` = "String",
        functionBody = IrFunctionBody(
          expression = IrExpression()
        )
      )
    }
  }
}
