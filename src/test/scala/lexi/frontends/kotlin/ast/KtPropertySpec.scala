package lexi.frontends.kotlin.ast

import lexi.KotlinTestUtils.TestCompiler
import lexi.frontends.kotlin.{KtFile, KtProperty}
import lexi.{Language, Source, KotlinTestUtils, Tree}
import lexi.frontends.kotlin.phases.SyntaxAnalysis

class KtPropertySpec extends munit.FunSuite {
  private def ktProperty(ast: Tree): KtProperty =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .get
      .head
      .declaration
      .get
      .propertyDeclaration
      .get

  test("integer declaration") {
    val code = """val x: Int = 5"""
    val ast = TestCompiler.ast(code)
    val property = ktProperty(ast)
    assert(property.isInstanceOf[KtProperty])
    assertEquals(property.name, Some("x"))
    assertEquals(property.expression, Some("5"))
    assertEquals(property.dataType, Some("Int"))
  }

  test("string declaration") {
    val code = """val firstName: String = "Matt""""
    val ast = TestCompiler.ast(code)
    val property = ktProperty(ast)
    assert(property.isInstanceOf[KtProperty])
    assertEquals(property.name, Some("firstName"))
    assertEquals(property.expression, Some(""""Matt""""))
    assertEquals(property.dataType, Some("String"))
  }

  test("empty string declaration") {
    val code = """val firstName: String = """""
    val ast = TestCompiler.ast(code)
    val property = ktProperty(ast)
    assert(property.isInstanceOf[KtProperty])
    assertEquals(property.name, Some("firstName"))
    assertEquals(property.expression, Some("\"\""))
    assertEquals(property.dataType, Some("String"))
  }
}
