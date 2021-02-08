package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.phases.SyntaxAnalysis

class KtFunctionSpec extends munit.FunSuite {
  private def ktFunction(ast: ASTNode): KtFunction =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .get
      .head
      .declaration
      .get
      .functionDeclaration
      .get

  test("expression function without parameters") {
    val source = """fun hello(): String = "Hello Maki!""""
    val ast = SyntaxAnalysis(source)
    val function = ktFunction(ast)
    assert(function.isInstanceOf[KtFunction])
    assertEquals(function.name, Some("hello"))
    assertEquals(function.`type`, Some("String"))
  }

  test("expression function with parameters") {
    val source = """fun hello(name: String): String = "Hello ${name}""""
    val ast = SyntaxAnalysis(source)
    val function = ktFunction(ast)
    assert(function.isInstanceOf[KtFunction])
    assertEquals(function.name, Some("hello"))
    assertEquals(function.`type`, Some("String"))
  }

  test("block function with parameters") {
    val source =
      """
        |fun hello(name: String): String {
        |  "Hello ${name}"
        |}
        |""".stripMargin
    val ast = SyntaxAnalysis(source)
    val function = ktFunction(ast)
    assert(function.isInstanceOf[KtFunction])
    assertEquals(function.name, Some("hello"))
    assertEquals(function.`type`, Some("String"))
  }
}
