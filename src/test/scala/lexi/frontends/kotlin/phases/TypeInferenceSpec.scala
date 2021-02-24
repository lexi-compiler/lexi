package lexi.frontends.kotlin.phases

import lexi.frontends.kotlin.{KtFile, KtNamedFunction, KtProperty}
import lexi.{Language, Source, Tree}
import lexi.frontends.kotlin.ast._

class TypeInferenceSpec extends munit.FunSuite {
  private def propertyNode(ast: Tree): KtProperty =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .get
      .head
      .declaration
      .get
      .propertyDeclaration
      .get

  private def functionNode(ast: Tree): KtNamedFunction =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .get
      .head
      .declaration
      .get
      .functionDeclaration
      .get

  test("infers integer value") {
    val code = "val x = 5"
    val source = Source.fromString(source = code, Language.Kotlin)
    val ast = propertyNode(SyntaxAnalysis(source))
    val typeInferredAst = TypeInference(ast)
    val expected = KtProperty(
      name = Some("x"),
      expression = Some("5"),
      dataType = Some("Int")
    )
    assertEquals(typeInferredAst, expected)
  }

  test("infers string value") {
    val code = """val x = "5""""
    val source = Source.fromString(source = code, Language.Kotlin)
    val ast = propertyNode(SyntaxAnalysis(source))
    val typeInferredAst = TypeInference(ast)
    val expected = KtProperty(
      name = Some("x"),
      expression = Some(""""5""""),
      dataType = Some("String")
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
