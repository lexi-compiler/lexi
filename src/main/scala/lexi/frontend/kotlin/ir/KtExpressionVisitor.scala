package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtExpressionContext}
import lexi.ir.nodes.IrExpression

object KtExpressionVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrExpression =
    ((_: KtExpressionContext) => IrExpression(
      
    ))(
      ast.asInstanceOf[KtExpressionContext]
    )
}
