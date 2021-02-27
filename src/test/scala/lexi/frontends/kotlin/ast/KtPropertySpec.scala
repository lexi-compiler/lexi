package lexi.frontends.kotlin.ast

import lexi.KotlinTestUtils.TestCompiler
import lexi.frontends.kotlin.{KtExpression, KtFile, KtProperty, KtSimpleIdentifier, KtType}
import lexi.{Language, Source, Tree}
import lexi.frontends.kotlin.phases.SyntaxAnalysis

class KtPropertySpec extends munit.FunSuite {
  private def ktProperty(ast: Tree): KtProperty =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
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
    assertEquals(property.name, Some(KtSimpleIdentifier("x")))
    assertEquals(property.`type`, Some(KtType("Int")))
  }

  test("string declaration") {
    val code = """val firstName: String = "Matt""""
    val ast = TestCompiler.ast(code)
    val property = ktProperty(ast)
    assert(property.isInstanceOf[KtProperty])
    assertEquals(property.name, Some(KtSimpleIdentifier("firstName")))
    assertEquals(property.`type`, Some(KtType("String")))
  }

  test("empty string declaration") {
    val code = """val firstName: String = """""
    val ast = TestCompiler.ast(code)
    val property = ktProperty(ast)
    assert(property.isInstanceOf[KtProperty])
    assertEquals(property.name, Some(KtSimpleIdentifier("firstName")))
    assertEquals(property.`type`, Some(KtType("String")))
  }
}
