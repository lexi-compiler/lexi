package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import java.util.Optional

case class KtFunctionBody(
  var block: KtBlock = null,
  var expression: KtExpression = null
) extends ASTNode

object KtFunctionBody extends KotlinParserBaseVisitor[KtFunctionBody] {
  override def visitFunctionBody(
    ctx: KotlinParser.FunctionBodyContext
  ): KtFunctionBody =
    new KtFunctionBody(
      block = Optional.ofNullable(ctx.block).map(KtBlock.visitBlock(_)).orElse(null),
      expression = Optional.ofNullable(ctx.expression).map(KtExpression.visitExpression(_)).orElse(null)
    ) {
      context = ctx
    }
}
