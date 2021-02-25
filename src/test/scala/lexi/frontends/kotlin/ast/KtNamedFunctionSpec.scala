package lexi.frontends.kotlin.ast

import lexi.KotlinTestUtils.TestCompiler
import lexi.frontends.kotlin.phases.SyntaxAnalysis
import lexi.frontends.kotlin.{KtFile, KtNamedFunction}
import lexi.{KotlinTestUtils, Language, Source, Tree}

class KtNamedFunctionSpec extends munit.FunSuite {
  private def ktFunction(ast: Tree): KtNamedFunction =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .get
      .head
      .declaration
      .get
      .functionDeclaration
      .get

  private def kotlinSource(code: String): Source =
    Source.fromString(code, Language.Kotlin)

  test("expression function without parameters") {
    val code =
      """
      fun hello(): String = "Hello Maki!"
      """.stripMargin
    val ast = TestCompiler.ast(code)
    val function = ktFunction(ast)
    assert(function.isInstanceOf[KtNamedFunction])
    assertEquals(function.name, Some("hello"))
    assertEquals(function.`type`, Some("String"))
  }

  test("expression function with parameters") {
    val code =
      """
      fun hello(name: String): String = "Hello ${name}"
      """.stripMargin
    val ast = TestCompiler.ast(code)
    val function = ktFunction(ast)
    assert(function.isInstanceOf[KtNamedFunction])
    assertEquals(function.name, Some("hello"))
    assertEquals(function.`type`, Some("String"))
  }

  test("block function with parameters") {
    val code = """
      fun hello(name: String): String {
        "Hello ${name}"
      }
      """.stripMargin
    val ast = TestCompiler.ast(code)
    val function = ktFunction(ast)
    assert(function.isInstanceOf[KtNamedFunction])
    assertEquals(function.name, Some("hello"))
    assertEquals(function.`type`, Some("String"))
  }
}
