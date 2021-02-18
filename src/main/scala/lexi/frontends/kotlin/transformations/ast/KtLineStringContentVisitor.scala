package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtLineStringContent}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtLineStringContentVisitor
  extends KotlinParserBaseVisitor[Option[AST] => KtLineStringContent] {
  override def visitLineStringContent(
    ctx: KotlinParser.LineStringContentContext
  ) = parentNode =>
    new KtLineStringContent {
      parent = parentNode
      context = Some(ctx)
      lineStrText = Try(ctx.LineStrText.toString).toOption
    }
}
