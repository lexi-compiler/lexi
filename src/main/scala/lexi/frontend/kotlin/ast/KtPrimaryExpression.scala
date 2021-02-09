package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtPrimaryExpression(
  var stringLiteral: Option[KtStringLiteral] = None
) extends ASTNode

object KtPrimaryExpression
  extends KotlinParserBaseVisitor[KtPrimaryExpression] {
  override def visitPrimaryExpression(
    ctx: KotlinParser.PrimaryExpressionContext
  ): KtPrimaryExpression =
    new KtPrimaryExpression {
      context = Some(ctx)
      stringLiteral = Try(KtStringLiteral.visit(ctx.stringLiteral)).toOption
    }
}
