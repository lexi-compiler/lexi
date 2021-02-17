package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtAdditiveExpressionVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtAdditiveExpression] {
  override def visitAdditiveExpression(ctx: KotlinParser.AdditiveExpressionContext) =
    parentNode =>
      new KtAdditiveExpression {
        parent = parentNode
        context = Some(ctx)
        multiplicativeExpressionContext = Try(
          ctx.multiplicativeExpression.asScala.toVector.map(
            KtMultiplicativeExpressionVisitor.visit(_)(
              Some(this.asInstanceOf[KtAdditiveExpression])
            )
          )
        ).toOption
      }
}
