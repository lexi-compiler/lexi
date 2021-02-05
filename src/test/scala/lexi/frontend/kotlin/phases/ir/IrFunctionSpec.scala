package lexi.frontend.kotlin.phases.ir

import lexi.frontend.kotlin.phases.LanguageAnalysis
import lexi.ir.nodes._

class IrFunctionSpec extends munit.FunSuite {
  private def node(ir: IrNode): IrFunction =
    ir.asInstanceOf[IrFile].topLevelObjects.head.declaration.functionDeclaration

  test("expression function without parameters") {
    val source = """fun hello(): String = "Hello Maki!""""
    val ir = LanguageAnalysis(source)
    val irFunction = node(ir)
    val expected = IrFunction(
      name = "hello",
      `type` = "String",
      functionBody = IrFunctionBody(
        expression = IrExpression()
      )
    )
    assertEquals(irFunction, expected)
  }
}
