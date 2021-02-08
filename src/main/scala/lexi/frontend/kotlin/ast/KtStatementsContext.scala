package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._

case class KtStatementsContext(
  var statement: Vector[KtStatementContext] = null
) extends ASTNode

object KtStatementsContext extends KotlinParserBaseVisitor[KtStatementsContext] {
  override def visitStatements(ctx: KotlinParser.StatementsContext): KtStatementsContext =
    new KtStatementsContext(
      statement = ctx.statement.asScala.toVector.map(KtStatementContext.visit(_))
    )
}


