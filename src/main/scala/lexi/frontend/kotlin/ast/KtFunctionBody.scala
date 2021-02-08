package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import java.util.Optional

case class KtFunctionBody(
  var block: KtBlock = null,
  var expression: KtExpressionContext = null
) extends ASTNode

object KtFunctionBody extends KotlinParserBaseVisitor[KtFunctionBody] {
  override def visitFunctionBody(
    ctx: KotlinParser.FunctionBodyContext
  ): KtFunctionBody =
    new KtFunctionBody(
      block = Optional.ofNullable(ctx.block).map(KtBlock.visit(_)).orElse(null),
      expression = Optional.ofNullable(ctx.expression).map(KtExpressionContext.visit(_)).orElse(null)
    ) {
      context = ctx
    }
}
