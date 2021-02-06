package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._

case class KtBlock(
  var expressions: Vector[KtStatementContext] = null
) extends ASTNode

object KtBlock extends KotlinParserBaseVisitor[KtBlock] {
  override def visitBlock(ctx: KotlinParser.BlockContext): KtBlock =
    new KtBlock {
//      expressions = ctx.statements.statement.toArray.map(KtStatementContext.visitStatement(_))
    }
}