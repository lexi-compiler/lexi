package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.util.Try

case class KtStatement(
  var expression: Option[KtExpressionContext] = None
) extends ASTNode

object KtStatement extends KotlinParserBaseVisitor[Option[ASTNode] => KtStatement] {
  override def visitStatement(ctx: KotlinParser.StatementContext) = { parentNode =>
    new KtStatement {
      parent = parentNode
      context = Some(ctx)
      expression = Try(KtExpressionContext.visit(ctx.expression)(Some(this.asInstanceOf[KtStatement]))).toOption
    }
  }
}
