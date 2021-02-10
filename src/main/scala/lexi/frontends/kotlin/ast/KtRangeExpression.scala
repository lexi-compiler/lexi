package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtRangeExpression(
  var additiveExpressions: Option[Vector[KtAdditiveExpression]] = None
) extends ASTNode

object KtRangeExpression extends KotlinParserBaseVisitor[Option[ASTNode] => KtRangeExpression] {
  override def visitRangeExpression(ctx: KotlinParser.RangeExpressionContext) = parentNode =>
    new KtRangeExpression {
      parent = parentNode
      context = Some(ctx)
      additiveExpressions = Try(
        ctx.additiveExpression.asScala.toVector.map(
          KtAdditiveExpression.visit(_)(Some(this.asInstanceOf[KtRangeExpression]))
        )
      ).toOption
    }
}
