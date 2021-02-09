package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

case class KtPostfixUnaryExpression() extends ASTNode

object KtPostfixUnaryExpression extends KotlinParserBaseVisitor[KtPostfixUnaryExpression] {
  override def visitPostfixUnaryExpression(ctx: KotlinParser.PostfixUnaryExpressionContext): KtPostfixUnaryExpression =
    new KtPostfixUnaryExpression {
      context = Some(ctx)
    }
}
