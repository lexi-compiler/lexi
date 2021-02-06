package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

case class KtStatementContext(
) extends ASTNode

object KtStatementContext extends KotlinParserBaseVisitor[KtStatementContext] {
  override def visitStatement(ctx: KotlinParser.StatementContext): KtStatementContext =
    new KtStatementContext {
      
    }
}
