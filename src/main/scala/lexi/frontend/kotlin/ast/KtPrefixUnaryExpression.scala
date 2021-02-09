package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtPrefixUnaryExpression(
  var postfixUnaryExpression: Option[KtPostfixUnaryExpression] = None
) extends ASTNode

object KtPrefixUnaryExpression
  extends KotlinParserBaseVisitor[KtPrefixUnaryExpression] {
  override def visitPrefixUnaryExpression(
    ctx: KotlinParser.PrefixUnaryExpressionContext
  ): KtPrefixUnaryExpression =
    new KtPrefixUnaryExpression {
      context = Some(ctx)
      postfixUnaryExpression = Try(KtPostfixUnaryExpression.visit(ctx.postfixUnaryExpression)).toOption
    }
}
