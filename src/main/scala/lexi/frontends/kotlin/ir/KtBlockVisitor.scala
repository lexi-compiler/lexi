package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.{AST, KtBlock}
import lexi.frontends.kotlin.transformations.ast.KtBlockVisitor
import lexi.ir.nodes.IrNode

object KtBlockVisitor extends KtVisitor {
  override def visit(ast: AST): IrNode =
    (
      (_: KtBlock) => new IrNode {}
    )(ast.asInstanceOf[KtBlock])
}
