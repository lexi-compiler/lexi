package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtAdditiveExpression(
  var multiplicativeExpressionContext: Option[Vector[KtMultiplicativeExpression]] = None
) extends ASTNode

object KtAdditiveExpression
  extends KotlinParserBaseVisitor[KtAdditiveExpression] {
  override def visitAdditiveExpression(
    ctx: KotlinParser.AdditiveExpressionContext
  ): KtAdditiveExpression =
    new KtAdditiveExpression {
      context = Some(ctx)
      multiplicativeExpressionContext = Try {
        ctx.multiplicativeExpression.asScala.toVector.map(KtMultiplicativeExpression.visit(_))
      }.toOption
    }
}
