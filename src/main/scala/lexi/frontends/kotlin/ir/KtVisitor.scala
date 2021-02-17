package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.Tree
import lexi.ir.nodes.IrNode

trait KtVisitor {
  def visit(ast: Tree): IrNode
}
