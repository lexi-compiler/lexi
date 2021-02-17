package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtStringLiteralVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtStringLiteral] {
  override def visitStringLiteral(ctx: KotlinParser.StringLiteralContext) = parentNode =>
    new KtStringLiteral {
      parent = parentNode
      context = Some(ctx)
      lineStringLiteral = Try(
        KtLineStringLiteralVisitor.visit(ctx.lineStringLiteral)(Some(this.asInstanceOf[KtStringLiteral]))
      ).toOption
    }
}
