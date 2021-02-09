package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtComparison(
  var genericCallLikeComparisonContext: Option[Vector[KtGenericCallLikeComparison]] = None
) extends ASTNode

object KtComparison extends KotlinParserBaseVisitor[KtComparison] {
  override def visitComparison(
    ctx: KotlinParser.ComparisonContext
  ): KtComparison =
    new KtComparison {
      context = Some(ctx)
      genericCallLikeComparisonContext = Try(
        ctx.genericCallLikeComparison.asScala.toVector.map(KtGenericCallLikeComparison.visit(_))
      ).toOption
    }
}
