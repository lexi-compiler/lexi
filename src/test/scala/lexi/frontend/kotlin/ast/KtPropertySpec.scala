package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.phases.SyntaxAnalysis

class KtPropertySpec extends munit.FunSuite {
  private def node(ast: ASTNode): KtProperty =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .head
      .declaration
      .propertyDeclaration

  test("integer declaration") {
    val source = """val x: Int = 5"""
    val ast = SyntaxAnalysis(source)
    val ktProperty = node(ast)
    val expected = KtProperty("x", "5", "Int")
    assertEquals(ktProperty, expected)
  }

  test("string declaration") {
    val ast = SyntaxAnalysis("""val firstName: String = "Matt"""")
    val ktProperty = node(ast)
    val expected = KtProperty(
      name = "firstName",
      expression = """"Matt"""",
      dataType = "String"
    )
    assertEquals(ktProperty, expected)
  }

  test("empty string declaration") {
    val ast = SyntaxAnalysis("""val firstName: String = """"")
    val ktProperty = node(ast)
    val expected = KtProperty(
      name = "firstName",
      expression = "\"\"",
      dataType = "String"
    )
    assertEquals(ktProperty, expected)
  }
}
