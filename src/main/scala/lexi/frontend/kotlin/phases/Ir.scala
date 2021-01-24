package lexi.frontend.kotlin.phases

import lexi.Phase
import lexi.frontend.kotlin.ast.ASTNode
import lexi.ir.Nodes.IrNode

object Ir extends Phase {
  def apply(ast: ASTNode): IrNode = {
    new IrNode {}
  }
}
