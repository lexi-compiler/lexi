package lexi.frontends.kotlin.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import java.util.Optional
import scala.util.Try

object KtFunctionBodyVisitor extends KotlinParserBaseVisitor[Option[Tree] => KtFunctionBody] {
  override def visitFunctionBody(ctx: KotlinParser.FunctionBodyContext) = { parentNode =>
    new KtFunctionBody {
      parent = parentNode
      context = Some(ctx)
      block = Try(KtBlockVisitor.visit(ctx.block)(Some(this.asInstanceOf[KtFunctionBody]))).toOption
      expression = Try(KtExpressionContextVisitor.visit(ctx.expression)(Some(this.asInstanceOf[KtFunctionBody]))).toOption
    }
  }
}
