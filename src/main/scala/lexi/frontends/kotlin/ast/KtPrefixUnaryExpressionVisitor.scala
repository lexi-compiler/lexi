package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtPrefixUnaryExpressionVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtPrefixUnaryExpression] {
  override def visitPrefixUnaryExpression(ctx: KotlinParser.PrefixUnaryExpressionContext) = { parentNode =>
    new KtPrefixUnaryExpression {
      parent = parentNode
      context = Some(ctx)
      postfixUnaryExpression = Try(KtPostfixUnaryExpressionVisitor.visit(ctx.postfixUnaryExpression)(Some(this.asInstanceOf[KtPrefixUnaryExpression]))).toOption
    }
  }
}
