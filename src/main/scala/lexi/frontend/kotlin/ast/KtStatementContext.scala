package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

case class KtStatementContext(
  var expression: KtExpressionContext = null
) extends ASTNode

object KtStatementContext extends KotlinParserBaseVisitor[KtStatementContext] {
  override def visitStatement(ctx: KotlinParser.StatementContext): KtStatementContext =
    new KtStatementContext(
      expression = KtExpressionContext.visit(ctx.expression)
    ) {
      context = ctx
    }
}
