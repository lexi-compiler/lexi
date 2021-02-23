package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtClass}

import scala.util.Try

object KtClassVisitor extends KotlinParserBaseVisitor[Option[AST] => KtClass] {
  override def visitClassDeclaration(
    ctx: KotlinParser.ClassDeclarationContext
  ) = { parentNode =>
    new KtClass {
      context = Some(ctx)
      parent = parentNode
      name = Some(ctx.simpleIdentifier.getText)
      primaryConstructor = Try(
        KtPrimaryConstructorVisitor.visit(ctx.primaryConstructor)(Some(this))
      ).toOption
      classBody = Try(KtClassBodyVisitor.visit(ctx.classBody)(Some(this))).toOption
    }
  }
}
