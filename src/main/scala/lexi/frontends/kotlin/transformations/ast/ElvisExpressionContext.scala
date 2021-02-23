package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtElvisExpression}

import scala.jdk.CollectionConverters._
import scala.util.Try

object ElvisExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtElvisExpression] {
  override def visitElvisExpression(ctx: KotlinParser.ElvisExpressionContext) = { parentNode =>
    new KtElvisExpression {
      parent = parentNode
      context = Some(ctx)
      infixFunctionCalls = Try(
        ctx.infixFunctionCall.asScala.toVector
          .map(InfixFunctionCallContext.visit(_)(Some(this)))
      ).toOption
    }
  }
}
