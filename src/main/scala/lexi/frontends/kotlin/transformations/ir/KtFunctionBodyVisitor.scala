package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtFunctionBody
import lexi.ir.IrFunctionBody

import scala.util.Try

object KtFunctionBodyVisitor extends Visitor[KtFunctionBody, IrFunctionBody] {
  override def visit(ast: KtFunctionBody): IrFunctionBody = {
    IrFunctionBody(
      block = None,
      expression = ast.expression.map(KtExpressionVisitor.visit(_))
    )
  }
}
