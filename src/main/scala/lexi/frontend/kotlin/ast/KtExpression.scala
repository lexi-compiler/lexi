package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

case class KtExpression(
  var disjunction: KtDisjunction = null
) extends ASTNode

object KtExpression extends KotlinParserBaseVisitor[ASTNode] {
  override def visitExpression(
    ctx: KotlinParser.ExpressionContext
  ): KtExpression =
    new KtExpression {
      context = ctx
      disjunction = KtDisjunction.visitDisjunction(ctx.disjunction)
      disjunction.parent = this
    }

}
