package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._

case class KtComparison(
  var genericCallLikeComparisonContext: Vector[
    KtGenericCallLikeComparison
  ] = null
) extends ASTNode

object KtComparison extends KotlinParserBaseVisitor[KtComparison] {
  override def visitComparison(
    ctx: KotlinParser.ComparisonContext
  ): KtComparison =
    new KtComparison(
      genericCallLikeComparisonContext =
        ctx.genericCallLikeComparison.asScala.toVector
          .map(KtGenericCallLikeComparison.visit(_))
    ) {
      context = ctx
    }
}
