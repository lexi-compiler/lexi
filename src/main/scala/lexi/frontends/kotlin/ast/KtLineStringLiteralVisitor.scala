package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object KtLineStringLiteralVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtLineStringLiteral] {
  override def visitLineStringLiteral(ctx: KotlinParser.LineStringLiteralContext) = parentNode =>
    new KtLineStringLiteral {
      parent = parentNode
      context = Some(ctx)
      lineStringContent = Try(
        ctx.lineStringContent.asScala.toVector.map(
          KtLineStringContentVisitor.visit(_)(Some(this.asInstanceOf[KtLineStringLiteral]))
        )
      ).toOption
    }
}
