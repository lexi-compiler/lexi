package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtElvisExpressionVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtElvisExpression] {
  override def visitElvisExpression(ctx: KotlinParser.ElvisExpressionContext) = { parentNode =>
    new KtElvisExpression {
      parent = parentNode
      context = Some(ctx)
      infixFunctionCalls = Try(
        ctx.infixFunctionCall.asScala.toVector.map(
          KtInfixFunctionCallVisitor.visit(_)(Some(this.asInstanceOf[KtElvisExpression]))
        )
      ).toOption
    }
  }
}
