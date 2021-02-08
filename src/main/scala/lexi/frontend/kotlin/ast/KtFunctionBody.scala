package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import java.util.Optional
import scala.util.Try

case class KtFunctionBody(
  var block: Option[KtBlock] = None,
  var expression: Option[KtExpressionContext] = None
) extends ASTNode

object KtFunctionBody extends KotlinParserBaseVisitor[KtFunctionBody] {
  override def visitFunctionBody(
    ctx: KotlinParser.FunctionBodyContext
  ): KtFunctionBody =
    new KtFunctionBody {
      context = Some(ctx)
      block = Try(KtBlock.visit(ctx.block)).toOption
      expression = Try(KtExpressionContext.visit(ctx.expression)).toOption
    }
}
