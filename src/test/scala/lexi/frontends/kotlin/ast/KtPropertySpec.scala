package lexi.frontends.kotlin.ast

import lexi.KotlinTestUtils.TestCompiler
import lexi.frontends.kotlin.{KtExpression, KtFile, KtNamedFunction, KtProperty, KtSimpleIdentifier, KtType}
import lexi.{Language, Source, Tree}
import lexi.frontends.kotlin.phases.SyntaxAnalysis

class KtPropertySpec extends munit.FunSuite {
  test("integer declaration") {
    val code = """val x: Int = 5"""
    val property = TestCompiler.ast(code, useAdaptiveLL = true).asInstanceOf[KtProperty]
    assertEquals(property.name, Some(KtSimpleIdentifier("x")))
    assertEquals(property.`type`, Some(KtType("Int")))
  }

  test("string declaration") {
    val code = """val firstName: String = "Matt""""
    val property = TestCompiler.ast(code, useAdaptiveLL = true).asInstanceOf[KtProperty]
    assertEquals(property.name, Some(KtSimpleIdentifier("firstName")))
    assertEquals(property.`type`, Some(KtType("String")))
  }

  test("empty string declaration") {
    val code = """val firstName: String = """""
    val property = TestCompiler.ast(code, useAdaptiveLL = true).asInstanceOf[KtProperty]
    assertEquals(property.name, Some(KtSimpleIdentifier("firstName")))
    assertEquals(property.`type`, Some(KtType("String")))
  }
}
