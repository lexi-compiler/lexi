package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import java.util.Optional
import scala.util.Try

case class KtFunctionBody(
  var block: Option[KtBlock] = None,
  var expression: Option[KtExpressionContext] = None
) extends ASTNode

object KtFunctionBody extends KotlinParserBaseVisitor[Option[ASTNode] => KtFunctionBody] {
  override def visitFunctionBody(ctx: KotlinParser.FunctionBodyContext) = { parentNode =>
    new KtFunctionBody {
      parent = parentNode
      context = Some(ctx)
      block = Try(KtBlock.visit(ctx.block)(Some(this.asInstanceOf[KtFunctionBody]))).toOption
      expression = Try(KtExpressionContext.visit(ctx.expression)(Some(this.asInstanceOf[KtFunctionBody]))).toOption
    }
  }
}
