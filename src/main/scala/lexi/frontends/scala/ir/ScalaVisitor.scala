package lexi.frontends.scala.ir

import lexi.ir.nodes.IrNode

import scala.meta.Tree

trait ScalaVisitor {
  def visit(tree: Tree): IrNode
}
