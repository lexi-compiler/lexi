package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtFunctionBody}
import lexi.ir.nodes.{IrFunctionBody, IrNode}

object KtFunctionBodyVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrFunctionBody = {
    val ktFunctionBody = ast.asInstanceOf[KtFunctionBody]
    new IrFunctionBody {
      block = null
      expression = KtExpressionVisitor.visit(ktFunctionBody.expression)
    }
  }
}
