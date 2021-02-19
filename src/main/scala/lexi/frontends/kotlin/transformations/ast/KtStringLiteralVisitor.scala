package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtStringLiteral}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtStringLiteralVisitor extends KotlinParserBaseVisitor[Option[AST] => KtStringLiteral] {
  override def visitStringLiteral(ctx: KotlinParser.StringLiteralContext) =
    parentNode =>
      new KtStringLiteral {
        parent = parentNode
        context = Some(ctx)
        lineStringLiteral = Try(
          KtLineStringLiteralVisitor.visit(ctx.lineStringLiteral)(Some(this))
        ).toOption
      }
}
