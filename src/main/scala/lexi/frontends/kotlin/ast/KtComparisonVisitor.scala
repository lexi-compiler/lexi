package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtComparisonVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtComparison] {
  override def visitComparison(ctx: KotlinParser.ComparisonContext) = parentNode =>
    new KtComparison {
      parent = parentNode
      context = Some(ctx)
      genericCallLikeComparisonContext = Try(
        ctx.genericCallLikeComparison.asScala.toVector.map(
          KtGenericCallLikeComparisonVisitor.visit(_)(Some(this.asInstanceOf[KtComparison]))
        )
      ).toOption
    }
}
