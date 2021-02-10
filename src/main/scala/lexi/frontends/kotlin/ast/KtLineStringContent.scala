package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtLineStringContent(
  var lineStrText: Option[String] = None
) extends ASTNode

object KtLineStringContent extends KotlinParserBaseVisitor[Option[ASTNode] => KtLineStringContent] {
  override def visitLineStringContent(ctx: KotlinParser.LineStringContentContext) = parentNode =>
    new KtLineStringContent {
      parent = parentNode
      context = Some(ctx)
      lineStrText = Try(ctx.LineStrText.toString).toOption
    }
}
