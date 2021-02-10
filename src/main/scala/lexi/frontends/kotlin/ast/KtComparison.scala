package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtComparison(
  var genericCallLikeComparisonContext: Option[Vector[KtGenericCallLikeComparison]] = None
) extends ASTNode

object KtComparison extends KotlinParserBaseVisitor[Option[ASTNode] => KtComparison] {
  override def visitComparison(ctx: KotlinParser.ComparisonContext) = parentNode =>
    new KtComparison {
      parent = parentNode
      context = Some(ctx)
      genericCallLikeComparisonContext = Try(
        ctx.genericCallLikeComparison.asScala.toVector.map(
          KtGenericCallLikeComparison.visit(_)(Some(this.asInstanceOf[KtComparison]))
        )
      ).toOption
    }
}
