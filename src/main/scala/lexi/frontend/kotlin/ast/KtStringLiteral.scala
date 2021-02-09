package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtStringLiteral(
  var lineStringLiteral: Option[KtLineStringLiteral] = None
) extends ASTNode

object KtStringLiteral extends KotlinParserBaseVisitor[KtStringLiteral] {
  override def visitStringLiteral(
    ctx: KotlinParser.StringLiteralContext
  ): KtStringLiteral =
    new KtStringLiteral {
      context = Some(ctx)
      lineStringLiteral = Try(KtLineStringLiteral.visit(ctx.lineStringLiteral)).toOption
    }
}
