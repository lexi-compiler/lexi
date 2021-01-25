package lexi.frontend.kotlin.ir
import lexi.frontend.kotlin.ast.ASTNode
import lexi.ir.nodes.IrNode

object KtBlockVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrNode = {
    new IrNode {}
  }
}
