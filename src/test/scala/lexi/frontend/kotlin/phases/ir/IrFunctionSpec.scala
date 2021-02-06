package lexi.frontend.kotlin.phases.ir

import lexi.frontend.kotlin.ast.{KtExpression, KtFunction, KtFunctionBody}
import lexi.frontend.kotlin.phases.{Ir, LanguageAnalysis, SemanticAnalysis}
import lexi.ir.nodes._

class IrFunctionSpec extends munit.FunSuite {
  test("expression function without parameters") {
    val ast = KtFunction(
      name = "hello",
      `type` = "String",
      functionBody = KtFunctionBody(expression = KtExpression())
    )
    val irFunction = Ir(ast)
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
