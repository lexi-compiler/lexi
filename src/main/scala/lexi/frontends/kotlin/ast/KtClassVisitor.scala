package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtClassVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtClass] {
  override def visitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) = { parentNode =>
    new KtClass {
      context = Some(ctx)
      parent = parentNode
      name = Some(ctx.simpleIdentifier.getText)
      primaryConstructor = Try {
        KtPrimaryConstructorVisitor.visit(ctx.primaryConstructor)(Some(this.asInstanceOf[KtClass]))
      }.toOption
      classBody = Try {
        KtClassBodyVisitor.visit(ctx.classBody)(Some(this.asInstanceOf[KtClass]))
      }.toOption
    }
  }
}
