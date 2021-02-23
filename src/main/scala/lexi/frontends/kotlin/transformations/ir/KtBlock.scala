package lexi.frontends.kotlin.transformations.ir

import lexi.frontends.kotlin.KtBlock
import lexi.ir.IrBlock
import lexi.{Tree, Visitor}

object KtBlock extends Visitor[KtBlock, IrBlock] {
  override def visit(ast: KtBlock): IrBlock = {
    new lexi.ir.IrBlock {}
  }
}
