package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.phases.SyntaxAnalysis

class KtFunctionSpec extends munit.FunSuite {
  private def node(ast: ASTNode): KtFunction =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .head
      .declaration
      .functionDeclaration

  test("expression function without parameters") {
    val source = """fun hello(): String = "Hello Maki!""""
    val ast = SyntaxAnalysis(source)
    val ktFunction = node(ast)
    val expected = KtFunction(
      name = "hello",
      `type` = "String",
      functionBody = KtFunctionBody(
        expression = KtExpression(
          KtDisjunction(Vector(KtConjunction(Vector(KtEquality()))))
        )
      )
    )
    assertEquals(ktFunction, expected)
  }

  test("expression function with parameters") {
    val source = """fun hello(name: String): String = "Hello ${name}""""
    val ast = SyntaxAnalysis(source)
    val ktFunction = node(ast)
    val expected =  KtFunction(
      name = "hello",
      `type` = "String",
      functionBody = KtFunctionBody(
        expression = KtExpression(
          KtDisjunction(Vector(KtConjunction(Vector(KtEquality()))))
        )
      )
    )
    assertEquals(ktFunction, expected)
  }

  test("block function with parameters") {
    val source =
      """
        |fun hello(name: String): String {
        |  "Hello ${name}"
        |}
        |""".stripMargin
    val ast = SyntaxAnalysis(source)
    val ktFunction = node(ast)
    val expected =  KtFunction(
      name = "hello",
      `type` = "String",
      functionBody = KtFunctionBody(
        block = KtBlock()
      )
    )
    assertEquals(ktFunction, expected)
  }
}
