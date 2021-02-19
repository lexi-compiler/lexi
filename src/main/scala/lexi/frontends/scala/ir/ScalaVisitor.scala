package lexi.frontends.scala.ir

import lexi.ir.IrTree

import scala.meta.Tree

trait ScalaVisitor {
  def visit(tree: Tree): IrTree
}
