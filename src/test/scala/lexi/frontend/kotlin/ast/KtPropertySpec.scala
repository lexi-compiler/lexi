package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.phases.SyntaxAnalysis

class KtPropertySpec extends munit.FunSuite {
  private def node(ast: ASTNode): KtProperty =
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
    val source = """val x: Int = 5"""
    val ast = SyntaxAnalysis(source)
    val ktProperty = node(ast)
    val expected = KtProperty(
      name = Some("x"),
      expression = Some("5"),
      dataType = Some("Int")
    )
    assertEquals(ktProperty, expected)
  }

  test("string declaration") {
    val ast = SyntaxAnalysis("""val firstName: String = "Matt"""")
    val ktProperty = node(ast)
    val expected = KtProperty(
      name = Some("firstName"),
      expression = Some(""""Matt""""),
      dataType = Some("String")
    )
    assertEquals(ktProperty, expected)
  }

  test("empty string declaration") {
    val ast = SyntaxAnalysis("""val firstName: String = """"")
    val ktProperty = node(ast)
    val expected = KtProperty(
      name = Some("firstName"),
      expression = Some("\"\""),
      dataType = Some("String")
    )
    assertEquals(ktProperty, expected)
  }
}
