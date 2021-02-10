package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.KotlinParser.DeclarationContext
import lexi.frontends.kotlin.antlr.KotlinParserBaseVisitor

import scala.util.Try

case class KtDeclaration(
  var propertyDeclaration: Option[KtProperty] = None,
  var functionDeclaration: Option[KtFunction] = None
) extends ASTNode

object KtDeclaration extends KotlinParserBaseVisitor[Option[ASTNode] => KtDeclaration] {
  override def visitDeclaration(ctx: DeclarationContext) = { parentNode =>
    new KtDeclaration {
      parent = parentNode
      context = Some(ctx)
      propertyDeclaration = Try(KtProperty.visit(ctx.propertyDeclaration)(Some(this.asInstanceOf[KtDeclaration]))).toOption
      functionDeclaration = Try(KtFunction.visit(ctx.functionDeclaration)(Some(this.asInstanceOf[KtDeclaration]))).toOption
    }
  }
}
