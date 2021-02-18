package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtBlock}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtBlockVisitor extends KotlinParserBaseVisitor[Option[AST] => KtBlock] {
  override def visitBlock(ctx: KotlinParser.BlockContext) = { parentNode =>
    new KtBlock {
      parent = parentNode
      context = Some(ctx)
      statements = Try(
        ctx.statements.statement.asScala.toVector
          .map(KtStatementVisitor.visit(_)(Some(this.asInstanceOf[KtBlock])))
      ).toOption
    }
  }
}
