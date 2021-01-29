package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtExpression}
import lexi.ir.nodes.IrExpression

object KtExpressionVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrExpression =
    ((_: KtExpression) => new IrExpression {})(
      ast.asInstanceOf[KtExpression]
    )
}
