package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtAsExpressionVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtAsExpression] {
  override def visitAsExpression(ctx: KotlinParser.AsExpressionContext) = parentNode =>
    new KtAsExpression {
      parent = parentNode
      context = Some(ctx)
      prefixUnaryExpression = Try(
        KtPrefixUnaryExpressionVisitor.visit(ctx.prefixUnaryExpression)(
          Some(this.asInstanceOf[KtAsExpression])
        )
      ).toOption
    }
}
