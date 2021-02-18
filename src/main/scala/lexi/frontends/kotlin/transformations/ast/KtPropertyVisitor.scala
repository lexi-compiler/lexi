package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtProperty}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtPropertyVisitor extends KotlinParserBaseVisitor[Option[AST] => KtProperty] {
  override def visitPropertyDeclaration(
    ctx: KotlinParser.PropertyDeclarationContext
  ) = { parentNode =>
    new KtProperty {
      parent = parentNode
      context = Some(ctx)
      name = Try(ctx.variableDeclaration.simpleIdentifier.getText).toOption
      expression = Try(ctx.expression.getText).toOption
      dataType = Try(ctx.variableDeclaration.`type`.getText).toOption
    }
  }
}
