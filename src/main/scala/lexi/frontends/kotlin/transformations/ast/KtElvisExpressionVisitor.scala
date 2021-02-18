package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtElvisExpression}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtElvisExpressionVisitor extends KotlinParserBaseVisitor[Option[AST] => KtElvisExpression] {
  override def visitElvisExpression(
    ctx: KotlinParser.ElvisExpressionContext
  ) = { parentNode =>
    new KtElvisExpression {
      parent = parentNode
      context = Some(ctx)
      infixFunctionCalls = Try(
        ctx.infixFunctionCall.asScala.toVector.map(
          KtInfixFunctionCallVisitor.visit(_)(
            Some(this.asInstanceOf[KtElvisExpression])
          )
        )
      ).toOption
    }
  }
}
