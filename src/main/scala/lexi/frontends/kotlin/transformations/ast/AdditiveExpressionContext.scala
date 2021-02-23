package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtAdditiveExpression}

import scala.jdk.CollectionConverters._
import scala.util.Try

object AdditiveExpressionContext
  extends KotlinParserBaseVisitor[Option[AST] => KtAdditiveExpression] {
  override def visitAdditiveExpression(ctx: KotlinParser.AdditiveExpressionContext) =
    parentNode =>
      new KtAdditiveExpression {
        parent = parentNode
        context = Some(ctx)
        multiplicativeExpression = Try(
          ctx.multiplicativeExpression.asScala.toVector
            .map(MultiplicativeExpressionContext.visit(_)(Some(this)))
        ).toOption
      }
}
