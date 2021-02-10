package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtPrefixUnaryExpression(
  var postfixUnaryExpression: Option[KtPostfixUnaryExpression] = None
) extends ASTNode

object KtPrefixUnaryExpression extends KotlinParserBaseVisitor[Option[ASTNode] => KtPrefixUnaryExpression] {
  override def visitPrefixUnaryExpression(ctx: KotlinParser.PrefixUnaryExpressionContext) = { parentNode =>
    new KtPrefixUnaryExpression {
      parent = parentNode
      context = Some(ctx)
      postfixUnaryExpression = Try(KtPostfixUnaryExpression.visit(ctx.postfixUnaryExpression)(Some(this.asInstanceOf[KtPrefixUnaryExpression]))).toOption
    }
  }
}
