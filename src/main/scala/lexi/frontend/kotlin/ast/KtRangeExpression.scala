package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtRangeExpression(
  var additiveExpressions: Option[Vector[KtAdditiveExpression]] = None
) extends ASTNode

object KtRangeExpression extends KotlinParserBaseVisitor[KtRangeExpression] {
  override def visitRangeExpression(ctx: KotlinParser.RangeExpressionContext): KtRangeExpression =
    new KtRangeExpression {
      context = Some(ctx)
      additiveExpressions = Try {
        ctx.additiveExpression.asScala.toVector.map(KtAdditiveExpression.visit(_))
      }.toOption
    }
}
