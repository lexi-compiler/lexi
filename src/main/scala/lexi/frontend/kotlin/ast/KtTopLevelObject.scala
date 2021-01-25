package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.KotlinParser.TopLevelObjectContext
import lexi.frontend.kotlin.antlr.KotlinParserBaseVisitor

case class KtTopLevelObject(
  var declaration: KtDeclaration = null
) extends ASTNode

object KtTopLevelObject extends KotlinParserBaseVisitor[ASTNode] {
  override def visitTopLevelObject(
    ctx: TopLevelObjectContext
  ): KtTopLevelObject =
    new KtTopLevelObject(
      declaration = KtDeclaration.visitDeclaration(ctx.declaration)
    ) {
      context = ctx
      declaration.parent = this
    }

}
