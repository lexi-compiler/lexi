package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtClass(
  var name: Option[String] = None,
  var primaryConstructor: Option[KtPrimaryConstructor] = None,
  var classBody: Option[KtClassBody] = None
) extends ASTNode

object KtClass extends KotlinParserBaseVisitor[Option[ASTNode] => KtClass] {
  override def visitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) = { parentNode =>
    new KtClass {
      context = Some(ctx)
      parent = parentNode
      name = Some(ctx.simpleIdentifier.getText)
      primaryConstructor = Try {
        KtPrimaryConstructor.visit(ctx.primaryConstructor)(Some(this.asInstanceOf[KtClass]))
      }.toOption
      classBody = Try {
        KtClassBody.visit(ctx.classBody)(Some(this.asInstanceOf[KtClass]))
      }.toOption
    }
  }
}
