package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.{KtClass, KtClassParameter, KtDeclaration, KtFile}
import lexi.frontends.kotlin.phases.SyntaxAnalysis
import lexi.{Language, Source, TestUtils, Tree}

class KtClassSpec extends munit.FunSuite {
  private def ktClass(ast: Tree): KtClass =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .get
      .head
      .declaration
      .get
      .classDeclaration
      .get


  private def classParameter(klass: KtClass, index: Int): KtClassParameter =
    klass.primaryConstructor.get.classParameters.get(index)

  private def methodDeclaration(klass: KtClass, index: Int): KtDeclaration =
    klass.classBody.get.classMemberDeclarations.get(index)

  test("class parses") {
    val code =
      """class Person(val firstName: String, val lastName: String) {
        |  fun greet() = "Hello, ${firstName}, ${lastName}"
        |}
        |""".stripMargin
    val source = TestUtils.kotlinSource(code)
    val ast = SyntaxAnalysis(source)
    val klass = ktClass(ast)
    assertEquals(klass.name, Some("Person"))

    assertEquals(classParameter(klass, 0).name, Some("firstName"))
    assertEquals(classParameter(klass, 0).`type`, Some("String"))

    assertEquals(classParameter(klass, 1).name, Some("lastName"))
    assertEquals(classParameter(klass, 1).`type`, Some("String"))

    assertEquals(
      methodDeclaration(klass, 0).functionDeclaration.get.name,
      Some("greet")
    )
  }
}
