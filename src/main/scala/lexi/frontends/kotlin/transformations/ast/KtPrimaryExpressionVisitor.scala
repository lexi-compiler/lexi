package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtPrimaryExpression}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtPrimaryExpressionVisitor
  extends KotlinParserBaseVisitor[Option[AST] => KtPrimaryExpression] {
  override def visitPrimaryExpression(
    ctx: KotlinParser.PrimaryExpressionContext
  ) = { parentNode =>
    new KtPrimaryExpression {
      parent = parentNode
      context = Some(ctx)
      stringLiteral = Try(
        KtStringLiteralVisitor.visit(ctx.stringLiteral)(Some(this))
      ).toOption
    }
  }
}
