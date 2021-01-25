package lexi.frontend.kotlin.phases

import lexi.Phase
import lexi.frontend.kotlin.ast.ASTNode
import lexi.frontend.kotlin.ir.KtFileVisitor
import lexi.ir.nodes.IrNode

object Ir extends Phase {
  def apply(ast: ASTNode): IrNode =
    KtFileVisitor.visit(ast)
}
