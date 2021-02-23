package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtExpressionContext
import lexi.ir.IrExpression

object KtExpressionVisitor extends Visitor[KtExpressionContext, IrExpression] {
  override def visit(ast: KtExpressionContext): IrExpression = {
    IrExpression()
  }
}
