package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtStringLiteral}

import scala.util.Try

object StringLiteralContext extends KotlinParserBaseVisitor[Option[AST] => KtStringLiteral] {
  override def visitStringLiteral(ctx: KotlinParser.StringLiteralContext) =
    parentNode =>
      new KtStringLiteral {
        parent = parentNode
        context = Some(ctx)
        lineStringLiteral = Try(
          LineStringLiteralContext.visit(ctx.lineStringLiteral)(Some(this))
        ).toOption
      }
}
