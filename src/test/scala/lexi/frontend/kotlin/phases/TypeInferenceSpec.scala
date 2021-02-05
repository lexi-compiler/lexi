//package lexi.frontend.kotlin.phases
//
//import lexi.frontend.kotlin.ast.{ASTNode, KtFile, KtProperty}
//
//class TypeInferenceSpec extends munit.FunSuite {
//  private def node(ast: ASTNode): KtProperty =
//    ast
//      .asInstanceOf[KtFile]
//      .topLevelObjects
//      .head
//      .declaration
//      .propertyDeclaration
//
//  test("infers integer value") {
//    val ast = SyntaxAnalysis("val x = 5")
//    val typeInferredAst = TypeInference(ast)
//    val typeInferredProperty = node(typeInferredAst)
//    val expected = KtProperty("x", "5", "Int")
//    assertEquals(typeInferredProperty, expected)
//  }
//}
