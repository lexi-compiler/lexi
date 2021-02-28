package lexi.frontends.kotlin.phases.ir

import lexi.KotlinTestUtils.TestCompiler
import lexi.frontends.kotlin.KtNamedFunction
import lexi.frontends.kotlin.phases.{Ir, LanguageAnalysis, SemanticAnalysis}
import lexi.ir.{IrExpression, IrFile, IrFunction, IrTree}

class IrFunctionSpec extends munit.FunSuite {
  private def irFunction(file: IrTree) =
    file.asInstanceOf[IrFile].topLevelObjects.head.declaration.get.functionDeclaration.get

  test("expression function without parameters") {
    val code = """fun hello(): String = "Hello World""""
    val ir = TestCompiler.ir(code)
    val function = irFunction(ir)
    assert(function.isInstanceOf[IrFunction])
    assertEquals(function.name, Some("hello"))
    assertEquals(function.`type`, Some("String"))
    assertEquals(function.bodyBlockExpression, None)
    assertEquals(function.bodyExpression, Some(IrExpression()))
  }
}
