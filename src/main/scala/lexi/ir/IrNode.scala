package lexi.ir

import scala.collection.mutable.ListBuffer

trait IrNode {
  var children: ListBuffer[IrNode] = ListBuffer()
}
