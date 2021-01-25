package lexi.frontend.kotlin.ir
import lexi.frontend.kotlin.ast.{ASTNode, KtExpression}
import lexi.ir.nodes.{IrExpression, IrNode}

object KtExpressionVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrExpression = {
    val ktExpression = ast.asInstanceOf[KtExpression]
    new IrExpression {}
  }
}
