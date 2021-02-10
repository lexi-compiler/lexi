package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtStringLiteral(
  var lineStringLiteral: Option[KtLineStringLiteral] = None
) extends ASTNode

object KtStringLiteral extends KotlinParserBaseVisitor[Option[ASTNode] => KtStringLiteral] {
  override def visitStringLiteral(ctx: KotlinParser.StringLiteralContext) = parentNode =>
    new KtStringLiteral {
      parent = parentNode
      context = Some(ctx)
      lineStringLiteral = Try(
        KtLineStringLiteral.visit(ctx.lineStringLiteral)(Some(this.asInstanceOf[KtStringLiteral]))
      ).toOption
    }
}
