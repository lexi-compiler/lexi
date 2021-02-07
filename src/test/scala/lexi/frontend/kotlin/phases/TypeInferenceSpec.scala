package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.ast.{ASTNode, KtExpression, KtFile, KtFunction, KtFunctionBody, KtProperty}

class TypeInferenceSpec extends munit.FunSuite {
  private def propertyNode(ast: ASTNode): KtProperty =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .head
      .declaration
      .propertyDeclaration

  private def functionNode(ast: ASTNode): KtFunction =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .head
      .declaration
      .functionDeclaration

  test("infers integer value") {
    val source = "val x = 5"
    val ast = propertyNode(SyntaxAnalysis(source))
    val typeInferredAst = TypeInference(ast)
    val expected = KtProperty(
      name = "x",
      expression = "5",
      dataType = "Int"
    )
    assertEquals(typeInferredAst, expected)
  }

  test("infers string value") {
    val source = """val x = "5""""
    val ast = propertyNode(SyntaxAnalysis(source))
    val typeInferredAst = TypeInference(ast)
    val expected = KtProperty(
      name = "x",
      expression = "\"5\"",
      dataType = "String"
    )
    assertEquals(typeInferredAst, expected)
  }

  // TODO: Write algorithm for detecting function type.
//  test("infers string function") {
//    val source = """fun hello() = "Hello World""""
//    val ast = functionNode(SyntaxAnalysis(source))
//    val typeInferredAst = TypeInference(ast)
//    val expected = KtFunction(
//      name = "hello",
//      `type` = "String",
//      functionBody = KtFunctionBody(expression = KtExpression())
//    )
//    assertEquals(typeInferredAst, expected)
//  }
}
