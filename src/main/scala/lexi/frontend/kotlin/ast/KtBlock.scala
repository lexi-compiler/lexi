package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtBlock(
  var statements: Option[Vector[KtStatement]] = None
) extends ASTNode

object KtBlock extends KotlinParserBaseVisitor[Option[ASTNode] => KtBlock] {
  override def visitBlock(ctx: KotlinParser.BlockContext) = { parentNode =>
    new KtBlock {
      parent = parentNode
      context = Some(ctx)
      statements = Try(ctx.statements.statement.asScala.toVector.map(KtStatement.visit(_)(Some(this.asInstanceOf[KtBlock])))).toOption
    }
  }
}
