package lexi.frontends.kotlin.transformations.ir

import lexi.frontends.kotlin.AST
import lexi.ir.IrTree

trait KtVisitor {
  def visit(ast: AST): IrTree
}
