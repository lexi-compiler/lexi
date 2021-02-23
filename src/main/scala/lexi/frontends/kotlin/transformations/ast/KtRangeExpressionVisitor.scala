package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtRangeExpression}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtRangeExpressionVisitor extends KotlinParserBaseVisitor[Option[AST] => KtRangeExpression] {
  override def visitRangeExpression(ctx: KotlinParser.RangeExpressionContext) =
    parentNode =>
      new KtRangeExpression {
        parent = parentNode
        context = Some(ctx)
        additiveExpressions = Try(
          ctx.additiveExpression.asScala.toVector
            .map(KtAdditiveExpressionVisitor.visit(_)(Some(this)))
        ).toOption
      }
}
