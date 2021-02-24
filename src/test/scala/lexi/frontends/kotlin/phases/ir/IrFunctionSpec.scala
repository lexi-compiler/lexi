package lexi.frontends.kotlin.phases.ir

import lexi.frontends.kotlin.KtNamedFunction
import lexi.frontends.kotlin.phases.{Ir, LanguageAnalysis, SemanticAnalysis}
import lexi.ir.IrFunction

class IrFunctionSpec extends munit.FunSuite {
  test("expression function without parameters") {
    val ast = KtNamedFunction(
      name = Some("hello"),
      `type` = Some("String")
    )
    val irFunction = Ir(ast)
    val expected = IrFunction(
      name = Some("hello"),
      `type` = Some("String")
    )
    assertEquals(irFunction, expected)
  }
}
