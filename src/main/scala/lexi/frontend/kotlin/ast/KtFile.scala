package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.KotlinParser.KotlinFileContext
import lexi.frontend.kotlin.antlr.KotlinParserBaseVisitor

import scala.jdk.CollectionConverters._

case class KtFile(
  var topLevelObjects: Vector[KtTopLevelObject] = Vector.empty
) extends ASTNode

object KtFile extends KotlinParserBaseVisitor[KtFile] {
  override def visitKotlinFile(ctx: KotlinFileContext): KtFile =
    new KtFile {
      context = ctx
      topLevelObjects = ctx.topLevelObject.asScala.toVector.map {
        topLevelObject =>
          val tlo = KtTopLevelObject.visit(topLevelObject)
          tlo.parent = this
          tlo.asInstanceOf[KtTopLevelObject]
      }
    }
}
