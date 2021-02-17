package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtInfixFunctionCallVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtInfixFunctionCall] {
  override def visitInfixFunctionCall(ctx: KotlinParser.InfixFunctionCallContext) = { parentNode =>
    new KtInfixFunctionCall {
      parent = parentNode
      context = Some(ctx)
      rangeExpressions = Try(
        ctx.rangeExpression.asScala.toVector.map(
          KtRangeExpressionVisitor.visit(_)(Some(this.asInstanceOf[KtInfixFunctionCall]))
        )
      ).toOption
    }
  }
}
