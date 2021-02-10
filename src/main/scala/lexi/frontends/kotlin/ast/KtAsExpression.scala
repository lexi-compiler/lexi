package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtAsExpression(
  var prefixUnaryExpression: Option[KtPrefixUnaryExpression] = None
) extends ASTNode

object KtAsExpression extends KotlinParserBaseVisitor[Option[ASTNode] => KtAsExpression] {
  override def visitAsExpression(ctx: KotlinParser.AsExpressionContext) = parentNode =>
    new KtAsExpression {
      parent = parentNode
      context = Some(ctx)
      prefixUnaryExpression = Try(
        KtPrefixUnaryExpression.visit(ctx.prefixUnaryExpression)(
          Some(this.asInstanceOf[KtAsExpression])
        )
      ).toOption
    }
}
