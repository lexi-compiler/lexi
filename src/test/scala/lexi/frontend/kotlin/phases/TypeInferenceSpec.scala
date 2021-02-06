package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.ast.{ASTNode, KtFile, KtProperty}

class TypeInferenceSpec extends munit.FunSuite {
  private def node(ast: ASTNode): KtProperty =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .head
      .declaration
      .propertyDeclaration

  test("infers integer value") {
    val ast = node(SyntaxAnalysis("val x = 5"))
    val typeInferredAst = TypeInference(ast)
    val expected = KtProperty(
      name = "x",
      expression = "5",
      dataType = "Int"
    )
    assertEquals(typeInferredAst, expected)
  }
}
