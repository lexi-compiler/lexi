package lexi.frontends.kotlin.ast

import lexi.KotlinTestUtils.TestCompiler
import lexi.frontends.kotlin.phases.SyntaxAnalysis
import lexi.frontends.kotlin.{KtFile, KtNamedFunction, KtSimpleIdentifier, KtType}
import lexi.{KotlinTestUtils, Language, Source, Tree}

class KtNamedFunctionSpec extends munit.FunSuite {
  test("expression function without parameters") {
    val code =
      """
      fun hello(): String = "Hello Maki!"
      """.stripMargin
    val function = TestCompiler.ast(code, useAdaptiveLL = true).asInstanceOf[KtNamedFunction]
    assertEquals(function.name, Some(KtSimpleIdentifier("hello")))
    assertEquals(function.`type`, Some(KtType("String")))
  }

  test("expression function with parameters") {
    val code =
      """
      fun hello(name: String): String = "Hello ${name}"
      """.stripMargin
    val function = TestCompiler.ast(code, useAdaptiveLL = true).asInstanceOf[KtNamedFunction]
    assertEquals(function.name, Some(KtSimpleIdentifier("hello")))
    assertEquals(function.`type`, Some(KtType("String")))
  }

  test("block function with parameters") {
    val code = """
      fun hello(name: String): String {
        "Hello ${name}"
      }
      """.stripMargin
    val function = TestCompiler.ast(code, useAdaptiveLL = true).asInstanceOf[KtNamedFunction]
    assertEquals(function.name, Some(KtSimpleIdentifier("hello")))
    assertEquals(function.`type`, Some(KtType("String")))
  }
}
