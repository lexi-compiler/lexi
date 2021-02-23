package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtBlock}

import scala.jdk.CollectionConverters._
import scala.util.Try

object BlockContext extends KotlinParserBaseVisitor[Option[AST] => KtBlock] {
  override def visitBlock(ctx: KotlinParser.BlockContext) = { parentNode =>
    new KtBlock {
      parent = parentNode
      context = Some(ctx)
      statements = Try(
        ctx.statements.statement.asScala.toVector
          .map(StatementContext.visit(_)(Some(this)))
      ).toOption
    }
  }
}
