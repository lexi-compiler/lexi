package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtLineStringLiteral(
  var lineStringContent: Option[Vector[KtLineStringContent]] = None
) extends ASTNode

object KtLineStringLiteral extends KotlinParserBaseVisitor[KtLineStringLiteral] {
  override def visitLineStringLiteral(ctx: KotlinParser.LineStringLiteralContext): KtLineStringLiteral =
    new KtLineStringLiteral {
      context = Some(ctx)
      lineStringContent = Try(ctx.lineStringContent.asScala.toVector.map(KtLineStringContent.visit(_))).toOption
    }
}
