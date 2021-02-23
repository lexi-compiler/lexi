package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtStatement}

import scala.util.Try

object StatementContext extends KotlinParserBaseVisitor[Option[AST] => KtStatement] {
  override def visitStatement(ctx: KotlinParser.StatementContext) = { parentNode =>
    new KtStatement {
      parent = parentNode
      context = Some(ctx)
      expression = Try(
        ExpressionContext.visit(ctx.expression)(Some(this))
      ).toOption
    }
  }
}
