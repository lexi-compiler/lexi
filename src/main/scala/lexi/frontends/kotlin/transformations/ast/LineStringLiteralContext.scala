package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.transformations.ast.LineStringContentContext
import lexi.frontends.kotlin.{AST, KtLineStringLiteral}

import scala.jdk.CollectionConverters._
import scala.util.Try

object LineStringLiteralContext
  extends KotlinParserBaseVisitor[Option[AST] => KtLineStringLiteral] {
  override def visitLineStringLiteral(ctx: KotlinParser.LineStringLiteralContext) = parentNode =>
    new KtLineStringLiteral {
      parent = parentNode
      context = Some(ctx)
      lineStringContent = Try(
        ctx.lineStringContent.asScala.toVector
          .map(LineStringContentContext.visit(_)(Some(this)))
      ).toOption
    }
}
