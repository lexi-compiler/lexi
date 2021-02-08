package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtStatementContext(
  var expression: Option[KtExpressionContext] = None
) extends ASTNode

object KtStatementContext extends KotlinParserBaseVisitor[KtStatementContext] {
  override def visitStatement(ctx: KotlinParser.StatementContext): KtStatementContext =
    new KtStatementContext {
      context = Some(ctx)
      expression = Try(KtExpressionContext.visit(ctx.expression)).toOption
    }
}
