package lexi.ir.Nodes

import scala.collection.mutable.ListBuffer

trait IrNode {
  var children: ListBuffer[IrNode] = ListBuffer()
}
