package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.phases.Parser
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class KtPropertySpec extends AnyFunSpec with Matchers {
  describe("integer declaration") {
    val ast = Parser.parse(Parser("""val x: Int = 5"""))
    it("parses a KtProperty") {
      node(ast) shouldBe KtProperty("x", "5", "Int")
    }
  }

  describe("string declaration") {
    val ast = Parser.parse(Parser("""val firstName: String = "Matt""""))
    it("parses a KtProperty") {
      node(ast) shouldBe KtProperty(
        name = "firstName",
        expression = """"Matt"""",
        dataType = "String"
      )
    }
  }

  describe("empty string declaration") {
    val ast = Parser.parse(Parser("""val firstName: String = """""))
    it("parses a KtProperty") {
      node(ast) shouldBe KtProperty(
        name = "firstName",
        expression = "\"\"",
        dataType = "String"
      )
    }
  }

  private def node(ast: ASTNode): KtProperty =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .head
      .declaration
      .propertyDeclaration
}
