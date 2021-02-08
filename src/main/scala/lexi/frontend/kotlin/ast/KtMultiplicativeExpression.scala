package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

case class KtMultiplicativeExpression(
) extends ASTNode

object KtMultiplicativeExpression
  extends KotlinParserBaseVisitor[KtMultiplicativeExpression] {
  override def visitMultiplicativeExpression(
    ctx: KotlinParser.MultiplicativeExpressionContext
  ): KtMultiplicativeExpression =
    new KtMultiplicativeExpression() {
      context = ctx
    }
}
