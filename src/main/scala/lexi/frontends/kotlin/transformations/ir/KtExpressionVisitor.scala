package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtExpressionContext
import lexi.ir.IrExpression

object KtExpressionVisitor extends Visitor[IrExpression] {
  override def visit(ast: Tree): IrExpression = {
    val exprCtx = ast.asInstanceOf[KtExpressionContext]
    IrExpression(
    )
  }
}
