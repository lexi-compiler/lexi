package lexi.frontend.kotlin.phases.ir

import lexi.frontend.kotlin.phases.LanguageAnalysis
import lexi.ir.nodes._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class IrFunctionSpec extends AnyFunSpec with Matchers {
  private def node(ir: IrNode): IrFunction =
    ir.asInstanceOf[IrFile].topLevelObjects.head.declaration.functionDeclaration

  describe("expression function without parameters") {
    val source = """fun hello(): String = "Hello Maki!""""
    val ir = LanguageAnalysis(source)

    it("parses an IrFunction") {
      node(ir) shouldBe IrFunction(
        name = "hello",
        `type` = "String",
        functionBody = IrFunctionBody(
          expression = IrExpression()
        )
      )
    }
  }
}
