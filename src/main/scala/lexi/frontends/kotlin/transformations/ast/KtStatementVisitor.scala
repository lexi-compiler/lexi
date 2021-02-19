package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.{AST, KtStatement}
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

object KtStatementVisitor extends KotlinParserBaseVisitor[Option[AST] => KtStatement] {
  override def visitStatement(ctx: KotlinParser.StatementContext) = { parentNode =>
    new KtStatement {
      parent = parentNode
      context = Some(ctx)
      expression = Try(
        KtExpressionContextVisitor.visit(ctx.expression)(Some(this))
      ).toOption
    }
  }
}
