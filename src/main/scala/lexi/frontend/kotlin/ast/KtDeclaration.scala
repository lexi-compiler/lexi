package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.KotlinParser.DeclarationContext
import lexi.frontend.kotlin.antlr.KotlinParserBaseVisitor

case class KtDeclaration(
  var propertyDeclaration: KtProperty = null,
  var functionDeclaration: KtFunction = null
) extends ASTNode

object KtDeclaration extends KotlinParserBaseVisitor[KtDeclaration] {
  override def visitDeclaration(
    ctx: DeclarationContext
  ): KtDeclaration =
    new KtDeclaration {
      context = ctx
      propertyDeclaration =
        if (ctx.propertyDeclaration == null) null
        else {
          val property =
            KtProperty.visit(ctx.propertyDeclaration)
          property.parent = this
          property
        }
      functionDeclaration =
        if (ctx.functionDeclaration == null) null
        else {
          val function =
            KtFunction.visit(ctx.functionDeclaration)
          function.parent = this
          function
        }
    }
}
