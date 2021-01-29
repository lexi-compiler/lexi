package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.phases.SyntaxAnalysis
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class KtFunctionSpec extends AnyFunSpec with Matchers:
  private def node(ast: ASTNode): KtFunction =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .head
      .declaration
      .functionDeclaration

  describe("expression function without parameters") {
    val source = """fun hello(): String = "Hello Maki!""""
    val ast = SyntaxAnalysis(source)

    it("parses a KtFunction")
      node(ast) shouldBe KtFunction(
        name = "hello",
        `type` = "String",
        functionBody = KtFunctionBody(
          expression = KtExpression(
            KtDisjunction(Vector(KtConjunction(Vector(KtEquality()))))
          )
        )
      )
  }

  describe("expression function with parameters") {
    val source = """fun hello(name: String): String = "Hello ${name}""""
    val ast = node(SyntaxAnalysis(source))

    it("parses a KtFunction")
      ast shouldBe KtFunction(
        name = "hello",
        `type` = "String",
        functionBody = KtFunctionBody(
          expression = KtExpression(
            KtDisjunction(Vector(KtConjunction(Vector(KtEquality()))))
          )
        )
      )
  }
