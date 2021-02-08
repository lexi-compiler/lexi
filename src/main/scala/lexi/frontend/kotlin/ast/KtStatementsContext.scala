package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtStatementsContext(
  var statement: Option[Vector[KtStatementContext]] = None
) extends ASTNode

object KtStatementsContext extends KotlinParserBaseVisitor[KtStatementsContext] {
  override def visitStatements(ctx: KotlinParser.StatementsContext): KtStatementsContext =
    new KtStatementsContext(
      statement = Try(ctx.statement.asScala.toVector.map(KtStatementContext.visit(_))).toOption
    )
}


