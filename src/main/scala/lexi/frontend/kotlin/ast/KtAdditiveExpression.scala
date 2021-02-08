package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._

case class KtAdditiveExpression(
  var multiplicativeExpressionContext: Vector[KtMultiplicativeExpression] = null
) extends ASTNode

object KtAdditiveExpression
  extends KotlinParserBaseVisitor[KtAdditiveExpression] {
  override def visitAdditiveExpression(
    ctx: KotlinParser.AdditiveExpressionContext
  ): KtAdditiveExpression =
    new KtAdditiveExpression(
      multiplicativeExpressionContext =
        ctx.multiplicativeExpression.asScala.toVector
          .map(KtMultiplicativeExpression.visit(_))
    ) {
      context = ctx
    }
}
