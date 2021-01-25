package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.ast.{ASTNode, KtProperty}
import lexi.frontend.kotlin.phases.Phase.parse
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class TypeInferenceSpec extends AnyFunSpec with Matchers {
  ignore("when integer value") {
    val ast = parse("val x = 5")
    it("infers integer") {
      node(ast) shouldBe KtProperty("x", "5", "Int")
    }
  }

  private def node(ast: ASTNode): ASTNode =
    ast.children.head.children.head.children.head
}
