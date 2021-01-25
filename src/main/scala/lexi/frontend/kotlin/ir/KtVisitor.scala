package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.ASTNode
import lexi.ir.nodes.IrNode

trait KtVisitor {
  def visit(ast: ASTNode): IrNode
}
