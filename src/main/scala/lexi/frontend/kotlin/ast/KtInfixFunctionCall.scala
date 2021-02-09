package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtInfixFunctionCall(
  var rangeExpressions: Option[Vector[KtRangeExpression]] = None
) extends ASTNode

object KtInfixFunctionCall extends KotlinParserBaseVisitor[Option[ASTNode] => KtInfixFunctionCall] {
  override def visitInfixFunctionCall(ctx: KotlinParser.InfixFunctionCallContext) = { parentNode =>
    new KtInfixFunctionCall {
      parent = parentNode
      context = Some(ctx)
      rangeExpressions = Try(
        ctx.rangeExpression.asScala.toVector.map(
          KtRangeExpression.visit(_)(Some(this.asInstanceOf[KtInfixFunctionCall]))
        )
      ).toOption
    }
  }
}
