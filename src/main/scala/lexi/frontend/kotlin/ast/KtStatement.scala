package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtStatement(
  var expression: Option[KtExpressionContext] = None
) extends ASTNode

object KtStatement extends KotlinParserBaseVisitor[KtStatement] {
  override def visitStatement(ctx: KotlinParser.StatementContext): KtStatement =
    new KtStatement {
      context = Some(ctx)
      expression = Try(KtExpressionContext.visit(ctx.expression)).toOption
    }
}
