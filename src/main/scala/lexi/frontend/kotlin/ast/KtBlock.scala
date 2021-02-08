package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtBlock(
  var statementsContext: Option[KtStatementsContext] = None
) extends ASTNode

object KtBlock extends KotlinParserBaseVisitor[KtBlock] {
  override def visitBlock(ctx: KotlinParser.BlockContext): KtBlock =
    new KtBlock {
      context = Some(ctx)
      statementsContext = Try {
        KtStatementsContext.visit(ctx.statements)
      }.toOption
    }
}
