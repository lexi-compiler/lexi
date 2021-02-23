package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtMultiplicativeExpression}

import scala.jdk.CollectionConverters._
import scala.util.Try

object MultiplicativeExpressionContext
  extends KotlinParserBaseVisitor[Option[AST] => KtMultiplicativeExpression] {
  override def visitMultiplicativeExpression(
    ctx: KotlinParser.MultiplicativeExpressionContext
  ) = parentNode =>
    new KtMultiplicativeExpression {
      parent = parentNode
      context = Some(ctx)
      asExpression = Try(
        ctx.asExpression.asScala.toVector
          .map(AsExpressionContext.visit(_)(Some(this)))
      ).toOption
    }
}
