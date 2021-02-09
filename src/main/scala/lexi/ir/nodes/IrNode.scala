package lexi.ir.nodes

import scala.collection.mutable.ListBuffer

trait IrNode {
  var parent: Option[IrNode] = None
}
