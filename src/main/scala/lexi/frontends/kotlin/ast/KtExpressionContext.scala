package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtExpressionContext(
  var disjunction: Option[KtDisjunction] = None
) extends ASTNode

object KtExpressionContext
  extends KotlinParserBaseVisitor[Option[ASTNode] => KtExpressionContext] {
  override def visitExpression(ctx: KotlinParser.ExpressionContext) = parentNode =>
    new KtExpressionContext {
      parent = parentNode
      context = Some(ctx)
      disjunction = Try(
        KtDisjunction.visit(ctx.disjunction)(Some(this.asInstanceOf[KtExpressionContext]))
      ).toOption
    }
}
