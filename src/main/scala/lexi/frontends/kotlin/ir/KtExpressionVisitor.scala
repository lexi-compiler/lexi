package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{Tree, KtExpressionContext}
import lexi.ir.nodes.IrExpression

object KtExpressionVisitor extends KtVisitor {
  override def visit(ast: Tree): IrExpression =
    ((_: KtExpressionContext) => IrExpression(
      
    ))(
      ast.asInstanceOf[KtExpressionContext]
    )
}
