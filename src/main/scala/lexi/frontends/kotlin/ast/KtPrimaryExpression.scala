package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtPrimaryExpression(
  var stringLiteral: Option[KtStringLiteral] = None
) extends ASTNode

object KtPrimaryExpression extends KotlinParserBaseVisitor[Option[ASTNode] => KtPrimaryExpression] {
  override def visitPrimaryExpression(ctx: KotlinParser.PrimaryExpressionContext) = { parentNode =>
    new KtPrimaryExpression {
      parent = parentNode
      context = Some(ctx)
      stringLiteral = Try(KtStringLiteral.visit(ctx.stringLiteral)(Some(this.asInstanceOf[KtPrimaryExpression]))).toOption
    }
  }
}
