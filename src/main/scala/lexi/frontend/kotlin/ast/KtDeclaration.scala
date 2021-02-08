package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.KotlinParser.DeclarationContext
import lexi.frontend.kotlin.antlr.KotlinParserBaseVisitor

import scala.util.Try

case class KtDeclaration(
  var propertyDeclaration: Option[KtProperty] = None,
  var functionDeclaration: Option[KtFunction] = None
) extends ASTNode

object KtDeclaration extends KotlinParserBaseVisitor[KtDeclaration] {
  override def visitDeclaration(
    ctx: DeclarationContext
  ): KtDeclaration =
    new KtDeclaration {
      context = Some(ctx)
      propertyDeclaration = Try(KtProperty.visit(ctx.propertyDeclaration)).toOption
      functionDeclaration = Try(KtFunction.visit(ctx.functionDeclaration)).toOption
    }
}
