package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtFunctionBody}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtFunctionBodyVisitor extends KotlinParserBaseVisitor[Option[AST] => KtFunctionBody] {
  override def visitFunctionBody(ctx: KotlinParser.FunctionBodyContext) = { parentNode =>
    new KtFunctionBody {
      parent = parentNode
      context = Some(ctx)
      block = Try(
        KtBlockVisitor.visit(ctx.block)(Some(this))
      ).toOption
      expression = Try(
        KtExpressionContextVisitor.visit(ctx.expression)(Some(this))
      ).toOption
    }
  }
}
