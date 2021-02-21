package lexi.frontends.kotlin.transformations.ir

import lexi.frontends.kotlin.KtBlock
import lexi.ir.IrBlock
import lexi.{Tree, Visitor}

object KtBlockVisitor extends Visitor[IrBlock] {
  override def visit(ast: Tree): IrBlock = {
    val ktBlock = ast.asInstanceOf[KtBlock]
    new lexi.ir.IrBlock {}
  }
}
