package lexi.frontends.kotlin.phases

import lexi.Phase
import lexi.frontends.kotlin.ast.ASTNode
import lexi.ir.{IrFile, IrNode}

object Ir extends Phase {
  def apply(ast: ASTNode): IrNode = {
    IrFile()
  }
}
