package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{ASTNode, KtBlock}
import lexi.ir.nodes.IrNode

object KtBlockVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrNode =
    (
      (_: KtBlock) => new IrNode {}
    )(ast.asInstanceOf[KtBlock])
}
