package lexi.frontends.kotlin.phases.ir

import lexi.frontends.kotlin.KtFunction
import lexi.frontends.kotlin.phases.{Ir, Frontend, SemanticAnalysis}
import lexi.ir.nodes._

class IrFunctionSpec extends munit.FunSuite {
  test("expression function without parameters") {
    val ast = KtFunction(
      name = Some("hello"),
      `type` = Some("String"),
      functionBody = None
    )
    val irFunction = Ir(ast)
    val expected = IrFunction(
      name = Some("hello"),
      `type` = Some("String"),
      functionBody = None
    )
    assertEquals(irFunction, expected)
  }
}
