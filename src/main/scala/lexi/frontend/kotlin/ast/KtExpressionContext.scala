package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

case class KtExpressionContext(
  var disjunction: KtDisjunction = null
) extends ASTNode

object KtExpressionContext extends KotlinParserBaseVisitor[KtExpressionContext] {
  override def visitExpression(
    ctx: KotlinParser.ExpressionContext
  ): KtExpressionContext =
    new KtExpressionContext(
      disjunction = KtDisjunction.visit(ctx.disjunction)
    ) {
      context = ctx
    }
}
