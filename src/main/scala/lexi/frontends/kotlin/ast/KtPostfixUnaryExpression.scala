package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

case class KtPostfixUnaryExpression() extends ASTNode

object KtPostfixUnaryExpression extends KotlinParserBaseVisitor[Option[ASTNode] => KtPostfixUnaryExpression] {
  override def visitPostfixUnaryExpression(ctx: KotlinParser.PostfixUnaryExpressionContext) = { parentNode =>
    new KtPostfixUnaryExpression {
      parent = parentNode
      context = Some(ctx)
    }
  }
}
