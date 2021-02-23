package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtTopLevelObject}

import scala.util.Try

object TopLevelObjectContext extends KotlinParserBaseVisitor[Option[AST] => KtTopLevelObject] {
  override def visitTopLevelObject(ctx: KotlinParser.TopLevelObjectContext) = { parentNode =>
    new KtTopLevelObject {
      parent = parentNode
      context = Some(ctx)
      declaration = Try(
        DeclarationContext.visit(ctx.declaration)(Some(this))
      ).toOption
    }
  }
}
