package lexi.frontends.kotlin.ast

import lexi.KotlinTestUtils.TestCompiler
import lexi.frontends.kotlin.phases.SyntaxAnalysis
import lexi.frontends.kotlin._
import lexi._

class KtClassSpec extends munit.FunSuite {
  private def ktClass(ast: Tree): KtClass =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .head
      .declaration
      .get
      .classDeclaration
      .get

  private def classParameter(klass: KtClass, index: Int): KtClassParameter =
    klass.primaryConstructor.get.classParameters(index)

  private def methodDeclaration(klass: KtClass, index: Int): KtDeclaration =
    klass.classBody.get.declarations(index)

  test("class parses") {
    val code =
      """class Person(val firstName: String, val lastName: String) {
        |  fun greet(): String = "Hello, ${firstName}, ${lastName}"
        |}
        |""".stripMargin
    val ast = TestCompiler.ast(code)
    val klass = ktClass(ast)
    assertEquals(klass.simpleIdentifier, Some(KtSimpleIdentifier("Person")))

    assertEquals(classParameter(klass, 0).name, Some(KtSimpleIdentifier("firstName")))
    assertEquals(classParameter(klass, 0).`type`, Some(KtType("String")))

    assertEquals(classParameter(klass, 1).name, Some(KtSimpleIdentifier("lastName")))
    assertEquals(classParameter(klass, 1).`type`, Some(KtType("String")))

    assertEquals(
      methodDeclaration(klass, 0).functionDeclaration.get.name,
      Some(KtSimpleIdentifier("greet"))
    )
  }
}
