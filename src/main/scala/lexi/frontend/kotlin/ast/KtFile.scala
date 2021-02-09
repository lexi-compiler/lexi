package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.KotlinParser.KotlinFileContext
import lexi.frontend.kotlin.antlr.KotlinParserBaseVisitor

import scala.jdk.CollectionConverters._
import scala.util.Try

case class KtFile(
  var topLevelObjects: Option[Vector[KtTopLevelObject]] = None
) extends ASTNode

object KtFile extends KotlinParserBaseVisitor[KtFile] {
  override def visitKotlinFile(ctx: KotlinFileContext): KtFile =
    new KtFile {
      context = Some(ctx)
      topLevelObjects = Try(
        ctx.topLevelObject.asScala.toVector.map(
          KtTopLevelObject.visit(_)(Some(this.asInstanceOf[KtFile]))
        )
      ).toOption
    }
}
