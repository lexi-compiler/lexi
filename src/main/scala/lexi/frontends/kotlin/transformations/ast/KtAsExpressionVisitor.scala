package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtAsExpression}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtAsExpressionVisitor extends KotlinParserBaseVisitor[Option[AST] => KtAsExpression] {
  override def visitAsExpression(ctx: KotlinParser.AsExpressionContext) =
    parentNode =>
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
