package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtExpressionContextVisitor
  extends KotlinParserBaseVisitor[Option[Tree] => KtExpressionContext] {
  override def visitExpression(ctx: KotlinParser.ExpressionContext) = parentNode =>
    new KtExpressionContext {
      parent = parentNode
      context = Some(ctx)
      disjunction = Try(
        KtDisjunctionVisitor.visit(ctx.disjunction)(Some(this.asInstanceOf[KtExpressionContext]))
      ).toOption
    }
}
