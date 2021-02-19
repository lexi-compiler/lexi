package lexi.frontends.kotlin.transformations.ir

import lexi.frontends.kotlin.{AST, KtFunctionBody}
import lexi.ir.IrFunctionBody

import scala.util.Try

object KtFunctionBodyVisitor extends KtVisitor {
  override def visit(ast: AST): IrFunctionBody = {
    val body = ast.asInstanceOf[KtFunctionBody]
    IrFunctionBody(
      block = None,
      expression = body.expression.map(KtExpressionVisitor.visit(_))
    )
  }
}
