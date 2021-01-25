package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

case class KtFunctionBody(
  var block: Vector[String] = null,
  var expression: KtExpression = null
) extends ASTNode

object KtFunctionBody extends KotlinParserBaseVisitor[ASTNode] {
  override def visitFunctionBody(
    ctx: KotlinParser.FunctionBodyContext
  ): KtFunctionBody =
    new KtFunctionBody(
      block = null,
      expression = KtExpression.visitExpression(ctx.expression)
    ) {
      context = ctx
      expression.parent = this
    }

}
