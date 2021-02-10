package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{ASTNode, KtExpressionContext}
import lexi.ir.nodes.IrExpression

object KtExpressionVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrExpression =
    ((_: KtExpressionContext) => IrExpression(
      
    ))(
      ast.asInstanceOf[KtExpressionContext]
    )
}
