package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtMultiplicativeExpressionVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtMultiplicativeExpression] {
  override def visitMultiplicativeExpression(ctx: KotlinParser.MultiplicativeExpressionContext) = parentNode =>
    new KtMultiplicativeExpression {
      parent = parentNode
      context = Some(ctx)
      asExpression = Try(
        ctx.asExpression.asScala.toVector.map(
          KtAsExpressionVisitor.visit(_)(Some(this.asInstanceOf[KtMultiplicativeExpression]))
        )
      ).toOption
    }
}
