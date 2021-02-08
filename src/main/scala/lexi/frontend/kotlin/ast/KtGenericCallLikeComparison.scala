package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtGenericCallLikeComparison(
  var infixOperationContext: Option[KtInfixOperation] = None
) extends ASTNode

object KtGenericCallLikeComparison
  extends KotlinParserBaseVisitor[KtGenericCallLikeComparison] {
  override def visitGenericCallLikeComparison(
    ctx: KotlinParser.GenericCallLikeComparisonContext
  ): KtGenericCallLikeComparison =
    new KtGenericCallLikeComparison {
      context = Some(ctx)
      infixOperationContext = Try(KtInfixOperation.visit(ctx.infixOperation)).toOption
    }
}
