package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtExpressionContext(
  var disjunction: Option[KtDisjunction] = None
) extends ASTNode

object KtExpressionContext extends KotlinParserBaseVisitor[KtExpressionContext] {
  override def visitExpression(
    ctx: KotlinParser.ExpressionContext
  ): KtExpressionContext =
    new KtExpressionContext {
      context = Some(ctx)
      disjunction = Try {
        KtDisjunction.visit(ctx.disjunction)
      }.toOption
    }
}
