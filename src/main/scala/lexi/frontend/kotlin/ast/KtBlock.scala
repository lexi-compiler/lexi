package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._

case class KtBlock(
  var statementsContext: KtStatementsContext = null
) extends ASTNode

object KtBlock extends KotlinParserBaseVisitor[KtBlock] {
  override def visitBlock(ctx: KotlinParser.BlockContext): KtBlock =
    new KtBlock(
      statementsContext = KtStatementsContext.visit(ctx.statements)
    ) {
      context = ctx
    }
}
