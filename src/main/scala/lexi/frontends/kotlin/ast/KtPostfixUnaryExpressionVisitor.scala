package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

object KtPostfixUnaryExpressionVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtPostfixUnaryExpression] {
  override def visitPostfixUnaryExpression(ctx: KotlinParser.PostfixUnaryExpressionContext) = { parentNode =>
    new KtPostfixUnaryExpression {
      parent = parentNode
      context = Some(ctx)
    }
  }
}
