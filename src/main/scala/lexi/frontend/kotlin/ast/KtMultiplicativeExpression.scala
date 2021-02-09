package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtMultiplicativeExpression(
  var asExpression: Option[Vector[KtAsExpression]] = None
) extends ASTNode

object KtMultiplicativeExpression extends KotlinParserBaseVisitor[KtMultiplicativeExpression] {
  override def visitMultiplicativeExpression(
    ctx: KotlinParser.MultiplicativeExpressionContext
  ): KtMultiplicativeExpression =
    new KtMultiplicativeExpression {
      context = Some(ctx)
      asExpression = Try(ctx.asExpression.asScala.toVector.map(KtAsExpression.visit(_))).toOption
    }
}
