package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtInfixFunctionCall}

import scala.jdk.CollectionConverters._
import scala.util.Try

object InfixFunctionCallContext
  extends KotlinParserBaseVisitor[Option[AST] => KtInfixFunctionCall] {
  override def visitInfixFunctionCall(
    ctx: KotlinParser.InfixFunctionCallContext
  ) = { parentNode =>
    new KtInfixFunctionCall {
      parent = parentNode
      context = Some(ctx)
      rangeExpressions = Try(
        ctx.rangeExpression.asScala.toVector
          .map(RangeExpressionContext.visit(_)(Some(this)))
      ).toOption
    }
  }
}
