package lexi.frontends.kotlin.transformations.ir

import lexi.frontends.kotlin.{AST, KtExpressionContext}
import lexi.ir.nodes.IrExpression

object KtExpressionVisitor extends KtVisitor {
  override def visit(ast: AST): IrExpression =
    (
      (_: KtExpressionContext) =>
        IrExpression(
        )
    )(
      ast.asInstanceOf[KtExpressionContext]
    )
}
