package lexi.frontends.kotlin.transformations.ir

import lexi.frontends.kotlin.{AST, KtBlock}
import lexi.frontends.kotlin.transformations.ast.KtBlockVisitor
import lexi.ir.nodes.IrTree

object KtBlockVisitor extends KtVisitor {
  override def visit(ast: AST): IrTree = {
    val ktBlock = ast.asInstanceOf[KtBlock]
    new IrTree {}
  }
}
