package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.KotlinParser.DeclarationContext
import lexi.frontends.kotlin.antlr.KotlinParserBaseVisitor
import lexi.frontends.kotlin.{AST, KtDeclaration}

import scala.util.Try

object KtDeclarationVisitor extends KotlinParserBaseVisitor[Option[AST] => KtDeclaration] {
  override def visitDeclaration(ctx: DeclarationContext) = { parentNode =>
    new KtDeclaration {
      parent = parentNode
      context = Some(ctx)
      classDeclaration = Try(
        KtClassVisitor.visit(ctx.classDeclaration)(Some(this))
      ).toOption
      propertyDeclaration = Try(
        KtPropertyVisitor.visit(ctx.propertyDeclaration)(Some(this))
      ).toOption
      functionDeclaration = Try(
        KtFunctionVisitor.visit(ctx.functionDeclaration)(Some(this))
      ).toOption
    }
  }
}
