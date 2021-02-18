package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.{AST, KtFile, KtFunction}
import lexi.{Language, Source}
import lexi.frontends.kotlin.phases.SyntaxAnalysis

class KtFunctionSpec extends munit.FunSuite {
  private def ktFunction(ast: AST): KtFunction =
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
    val code = """fun hello(): String = "Hello Maki!""""
    val source = Source.fromString(source = code, Language.Kotlin)
    val ast = SyntaxAnalysis(source)
    val function = ktFunction(ast)
    assert(function.isInstanceOf[KtFunction])
    assertEquals(function.name, Some("hello"))
    assertEquals(function.`type`, Some("String"))
  }

  test("expression function with parameters") {
    val code = """fun hello(name: String): String = "Hello ${name}""""
    val source = Source.fromString(source = code, Language.Kotlin)
    val ast = SyntaxAnalysis(source)
    val function = ktFunction(ast)
    assert(function.isInstanceOf[KtFunction])
    assertEquals(function.name, Some("hello"))
    assertEquals(function.`type`, Some("String"))
  }

  test("block function with parameters") {
    val code =
      """
        |fun hello(name: String): String {
        |  "Hello ${name}"
        |}
        |""".stripMargin
    val source = Source.fromString(source = code, Language.Kotlin)
    val ast = SyntaxAnalysis(source)
    val function = ktFunction(ast)
    assert(function.isInstanceOf[KtFunction])
    assertEquals(function.name, Some("hello"))
    assertEquals(function.`type`, Some("String"))
  }
}
