package lexi.frontends.scala.ir

import lexi.ir.nodes.IrTree

import scala.meta.Tree

trait ScalaVisitor {
  def visit(tree: Tree): IrTree
}
