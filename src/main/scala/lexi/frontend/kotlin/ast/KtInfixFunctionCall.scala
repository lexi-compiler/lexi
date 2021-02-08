package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._

case class KtInfixFunctionCall(
  var rangeExpressions: Vector[KtRangeExpression] = null
) extends ASTNode

object KtInfixFunctionCall
  extends KotlinParserBaseVisitor[KtInfixFunctionCall] {
  override def visitInfixFunctionCall(
    ctx: KotlinParser.InfixFunctionCallContext
  ): KtInfixFunctionCall =
    new KtInfixFunctionCall(
      rangeExpressions =
        ctx.rangeExpression.asScala.toVector.map(KtRangeExpression.visit(_))
    ) {
      context = ctx
    }
}
