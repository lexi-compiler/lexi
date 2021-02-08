package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtInfixFunctionCall(
  var rangeExpressions: Option[Vector[KtRangeExpression]] = None
) extends ASTNode

object KtInfixFunctionCall
  extends KotlinParserBaseVisitor[KtInfixFunctionCall] {
  override def visitInfixFunctionCall(ctx: KotlinParser.InfixFunctionCallContext): KtInfixFunctionCall =
    new KtInfixFunctionCall {
      context = Some(ctx)
      rangeExpressions = Try {
        ctx.rangeExpression.asScala.toVector.map(KtRangeExpression.visit(_))
      }.toOption
    }
}
