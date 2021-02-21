package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtBlock

object KtBlockVisitor extends Visitor {
  override def visit(ast: Tree): Tree = {
    val ktBlock = ast.asInstanceOf[KtBlock]
    new lexi.ir.IrTree {}
  }
}
