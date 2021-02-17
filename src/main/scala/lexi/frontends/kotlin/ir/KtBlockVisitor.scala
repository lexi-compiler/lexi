package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{Tree, KtBlock}
import lexi.ir.nodes.IrNode

object KtBlockVisitor extends KtVisitor {
  override def visit(ast: Tree): IrNode =
    (
      (_: KtBlock) => new IrNode {}
    )(ast.asInstanceOf[KtBlock])
}
