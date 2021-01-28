package lexi.ir.nodes

import scala.collection.mutable.ListBuffer

trait IrNode {
  var children: ListBuffer[IrNode] = ListBuffer()
}

trait IrVisitor[T] {
  def visit(ir: IrNode): T
}
