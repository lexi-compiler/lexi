package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtLineStringContentVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtLineStringContent] {
  override def visitLineStringContent(ctx: KotlinParser.LineStringContentContext) = parentNode =>
    new KtLineStringContent {
      parent = parentNode
      context = Some(ctx)
      lineStrText = Try(ctx.LineStrText.toString).toOption
    }
}
