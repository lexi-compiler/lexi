package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtFunctionBody}

import scala.util.Try

object FunctionBodyContext extends KotlinParserBaseVisitor[Option[AST] => KtFunctionBody] {
  override def visitFunctionBody(ctx: KotlinParser.FunctionBodyContext) = { parentNode =>
    new KtFunctionBody {
      parent = parentNode
      context = Some(ctx)
      block = Try(
        BlockContext.visit(ctx.block)(Some(this))
      ).toOption
      expression = Try(
        ExpressionContext.visit(ctx.expression)(Some(this))
      ).toOption
    }
  }
}
