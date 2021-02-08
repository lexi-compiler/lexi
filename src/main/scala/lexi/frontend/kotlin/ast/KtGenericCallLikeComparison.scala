package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

case class KtGenericCallLikeComparison(
  var infixOperationContext: KtInfixOperation = null
) extends ASTNode

object KtGenericCallLikeComparison
  extends KotlinParserBaseVisitor[KtGenericCallLikeComparison] {
  override def visitGenericCallLikeComparison(
    ctx: KotlinParser.GenericCallLikeComparisonContext
  ): KtGenericCallLikeComparison =
    new KtGenericCallLikeComparison(
      infixOperationContext = KtInfixOperation.visit(ctx.infixOperation)
    ) {
      context = ctx
    }
}
