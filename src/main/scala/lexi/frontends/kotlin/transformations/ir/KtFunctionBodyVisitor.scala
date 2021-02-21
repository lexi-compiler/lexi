package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtFunctionBody
import lexi.ir.IrFunctionBody

import scala.util.Try

object KtFunctionBodyVisitor extends Visitor {
  override def visit(ast: Tree): IrFunctionBody = {
    val body = ast.asInstanceOf[KtFunctionBody]
    IrFunctionBody(
      block = None,
      expression = body.expression.map(KtExpressionVisitor.visit(_))
    )
  }
}
