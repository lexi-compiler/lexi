package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtTopLevelObject}
import lexi.frontends.kotlin.antlr.KotlinParser.TopLevelObjectContext
import lexi.frontends.kotlin.antlr.KotlinParserBaseVisitor

import scala.util.Try

object KtTopLevelObjectVisitor extends KotlinParserBaseVisitor[Option[AST] => KtTopLevelObject] {
  override def visitTopLevelObject(ctx: TopLevelObjectContext) = { parentNode =>
    new KtTopLevelObject {
      parent = parentNode
      context = Some(ctx)
      declaration = Try(
        KtDeclarationVisitor.visit(ctx.declaration)(
          Some(this.asInstanceOf[KtTopLevelObject])
        )
      ).toOption
    }
  }
}
