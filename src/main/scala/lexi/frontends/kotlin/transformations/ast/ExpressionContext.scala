package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtExpressionContext}

import scala.util.Try

object ExpressionContext
  extends KotlinParserBaseVisitor[Option[AST] => KtExpressionContext] {
  override def visitExpression(ctx: KotlinParser.ExpressionContext) =
    parentNode =>
      new KtExpressionContext {
        parent = parentNode
        context = Some(ctx)
        disjunction = Try(DisjunctionContext.visit(ctx.disjunction)(Some(this))).toOption
      }
}
