package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtLineStringContent(
  var lineStrText: Option[String] = None
) extends ASTNode

object KtLineStringContent
  extends KotlinParserBaseVisitor[KtLineStringContent] {
  override def visitLineStringContent(
    ctx: KotlinParser.LineStringContentContext
  ): KtLineStringContent =
    new KtLineStringContent {
      context = Some(ctx)
      lineStrText = Try(ctx.LineStrText.toString).toOption
    }
}
