package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.ASTNode
import lexi.ir.nodes.IrNode

trait KtVisitor {
  def visit(ast: ASTNode): IrNode
}
