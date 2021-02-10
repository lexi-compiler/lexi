package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtMultiplicativeExpression(
  var asExpression: Option[Vector[KtAsExpression]] = None
) extends ASTNode

object KtMultiplicativeExpression extends KotlinParserBaseVisitor[Option[ASTNode] => KtMultiplicativeExpression] {
  override def visitMultiplicativeExpression(ctx: KotlinParser.MultiplicativeExpressionContext) = parentNode =>
    new KtMultiplicativeExpression {
      parent = parentNode
      context = Some(ctx)
      asExpression = Try(
        ctx.asExpression.asScala.toVector.map(
          KtAsExpression.visit(_)(Some(this.asInstanceOf[KtMultiplicativeExpression]))
        )
      ).toOption
    }
}
