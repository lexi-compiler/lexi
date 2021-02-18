package lexi.frontends.kotlin.transformations.ir

import lexi.frontends.kotlin.AST
import lexi.ir.nodes.IrNode

trait KtVisitor {
  def visit(ast: AST): IrNode
}
