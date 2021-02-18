package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtPostfixUnaryExpression}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

object KtPostfixUnaryExpressionVisitor
  extends KotlinParserBaseVisitor[Option[AST] => KtPostfixUnaryExpression] {
  override def visitPostfixUnaryExpression(
    ctx: KotlinParser.PostfixUnaryExpressionContext
  ) = { parentNode =>
    new KtPostfixUnaryExpression {
      parent = parentNode
      context = Some(ctx)
    }
  }
}
