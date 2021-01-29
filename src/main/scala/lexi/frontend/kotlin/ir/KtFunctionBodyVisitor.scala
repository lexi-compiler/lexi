package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtFunctionBody}
import lexi.ir.nodes.IrFunctionBody

object KtFunctionBodyVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrFunctionBody =
    (
      (body: KtFunctionBody) =>
        IrFunctionBody(
          block = null,
          expression = KtExpressionVisitor.visit(body.expression)
        )
    )(ast.asInstanceOf[KtFunctionBody])
}
