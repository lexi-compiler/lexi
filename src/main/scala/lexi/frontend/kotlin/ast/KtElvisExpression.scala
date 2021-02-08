package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtElvisExpression(
  var infixFunctionCalls: Option[Vector[KtInfixFunctionCall]] = None
) extends ASTNode

object KtElvisExpression extends KotlinParserBaseVisitor[KtElvisExpression] {
  override def visitElvisExpression(
    ctx: KotlinParser.ElvisExpressionContext
  ): KtElvisExpression =
    new KtElvisExpression {
      context = Some(ctx)
      infixFunctionCalls = Try {
        ctx.infixFunctionCall.asScala.toVector.map(KtInfixFunctionCall.visit(_))
      }.toOption
    }
}
