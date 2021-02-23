package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtFunction}

import scala.util.Try

object FunctionDeclarationContext extends KotlinParserBaseVisitor[Option[AST] => KtFunction] {
  override def visitFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) = {
    parentNode =>
      new KtFunction {
        parent = parentNode
        context = Some(ctx)
        name = Try(ctx.simpleIdentifier.getText).toOption
        `type` = Try(ctx.`type`.getText).toOption
        functionBody = Try(
          FunctionBodyContext.visit(ctx.functionBody)(Some(this))
        ).toOption
      }
  }
}
