package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.{KtFile, KtProperty}
import lexi.{Language, Source, TestUtils, Tree}
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
    val source = Source.fromString(source = code, Language.Kotlin)
    val ast = SyntaxAnalysis(source)
    val property = ktProperty(ast)
    val expected = KtProperty(
      name = Some("x"),
      expression = Some("5"),
      dataType = Some("Int")
    )
    assertEquals(property, expected)
  }

  test("string declaration") {
    val code = """val firstName: String = "Matt""""
    val source = TestUtils.kotlinSource(code)
    val ast = SyntaxAnalysis(source)
    val property = ktProperty(ast)
    assert(property.isInstanceOf[KtProperty])
    assertEquals(property.name, Some("firstName"))
    assertEquals(property.expression, Some(""""Matt""""))
    assertEquals(property.dataType, Some("String"))
  }

  test("empty string declaration") {
    val code = """val firstName: String = """""
    val source = TestUtils.kotlinSource(code)
    val ast = SyntaxAnalysis(source)
    val property = ktProperty(ast)
    assert(property.isInstanceOf[KtProperty])
    assertEquals(property.name, Some("firstName"))
    assertEquals(property.expression, Some("\"\""))
    assertEquals(property.dataType, Some("String"))
  }
}
