package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._

case class KtInfixOperation(
  var elvisExpression: Vector[KtElvisExpression] = null
) extends ASTNode

object KtInfixOperation extends KotlinParserBaseVisitor[KtInfixOperation] {
  override def visitInfixOperation(
    ctx: KotlinParser.InfixOperationContext
  ): KtInfixOperation =
    new KtInfixOperation(
      elvisExpression =
        ctx.elvisExpression.asScala.toVector.map(KtElvisExpression.visit(_))
    ) {
      context = ctx
    }
}
