package lexi.frontends.kotlin.phases

import lexi.KotlinTestUtils.TestCompiler
import lexi.frontends.kotlin.{KtFile, KtNamedFunction, KtProperty}
import lexi.{Language, Source, Tree}
import lexi.frontends.kotlin.ast._

class TypeInferenceSpec extends munit.FunSuite {
  private def propertyNode(ast: Tree): KtProperty =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .head
      .declaration
      .get
      .propertyDeclaration
      .get

  private def functionNode(ast: Tree): KtNamedFunction =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .head
      .declaration
      .get
      .functionDeclaration
      .get

  // TODO: Write algorithm for type inference.

//  test("infers integer value") {
//    val code = "val x = 5"
//    val ast = propertyNode(TestCompiler.ast(code))
//    assert(ast.isInstanceOf[KtProperty])
//    assertEquals(ast.name, Some("x"))
//    assertEquals(ast.expression, Some("5"))
//    assertEquals(ast.dataType, Some("Int"))
//  }

//  test("infers string value") {
//    val code = """val x = "5""""
//    val ast = propertyNode(TestCompiler.ast(code))
//    assert(ast.isInstanceOf[KtProperty])
//    assertEquals(ast.name, Some("x"))
//    assertEquals(ast.expression, Some("\"5\""))
//    assertEquals(ast.dataType, Some("String"))
//  }

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
