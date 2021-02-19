package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtMultiplicativeExpression}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtMultiplicativeExpressionVisitor
  extends KotlinParserBaseVisitor[Option[AST] => KtMultiplicativeExpression] {
  override def visitMultiplicativeExpression(
    ctx: KotlinParser.MultiplicativeExpressionContext
  ) = parentNode =>
    new KtMultiplicativeExpression {
      parent = parentNode
      context = Some(ctx)
      asExpression = Try(
        ctx.asExpression.asScala.toVector
          .map(KtAsExpressionVisitor.visit(_)(Some(this)))
      ).toOption
    }
}
