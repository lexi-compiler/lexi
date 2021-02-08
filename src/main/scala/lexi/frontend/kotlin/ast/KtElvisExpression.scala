package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._

case class KtElvisExpression(
  var infixFunctionCalls: Vector[KtInfixFunctionCall] = null
) extends ASTNode

object KtElvisExpression extends KotlinParserBaseVisitor[KtElvisExpression] {
  override def visitElvisExpression(
    ctx: KotlinParser.ElvisExpressionContext
  ): KtElvisExpression =
    new KtElvisExpression(
      infixFunctionCalls =
        ctx.infixFunctionCall.asScala.toVector.map(KtInfixFunctionCall.visit(_))
    ) {
      context = ctx
    }
}
