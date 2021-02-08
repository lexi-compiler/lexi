package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._

case class KtRangeExpression(
  var additiveExpressions: Vector[KtAdditiveExpression] = null
) extends ASTNode

object KtRangeExpression extends KotlinParserBaseVisitor[KtRangeExpression] {
  override def visitRangeExpression(
    ctx: KotlinParser.RangeExpressionContext
  ): KtRangeExpression =
    new KtRangeExpression(
      additiveExpressions = ctx.additiveExpression.asScala.toVector
        .map(KtAdditiveExpression.visit(_))
    ) {
      context = ctx
    }
}
