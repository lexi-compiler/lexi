package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.KotlinParser.TopLevelObjectContext
import lexi.frontend.kotlin.antlr.KotlinParserBaseVisitor

import scala.util.Try

case class KtTopLevelObject(
  var declaration: Option[KtDeclaration] = None
) extends ASTNode

object KtTopLevelObject extends KotlinParserBaseVisitor[KtTopLevelObject] {
  override def visitTopLevelObject(
    ctx: TopLevelObjectContext
  ): KtTopLevelObject =
    new KtTopLevelObject {
      context = Some(ctx)
      declaration = Try(KtDeclaration.visit(ctx.declaration)).toOption
    }
}
