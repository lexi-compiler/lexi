package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtLineStringLiteral(
  var lineStringContent: Option[Vector[KtLineStringContent]] = None
) extends ASTNode

object KtLineStringLiteral extends KotlinParserBaseVisitor[Option[ASTNode] => KtLineStringLiteral] {
  override def visitLineStringLiteral(ctx: KotlinParser.LineStringLiteralContext) = parentNode =>
    new KtLineStringLiteral {
      parent = parentNode
      context = Some(ctx)
      lineStringContent = Try(
        ctx.lineStringContent.asScala.toVector.map(
          KtLineStringContent.visit(_)(Some(this.asInstanceOf[KtLineStringLiteral]))
        )
      ).toOption
    }
}
