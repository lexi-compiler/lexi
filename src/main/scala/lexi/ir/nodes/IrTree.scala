package lexi.ir.nodes

import lexi.Tree

trait IrTree extends Tree {
  var parent: Option[IrTree] = None
  var children: List[IrTree] = List.empty
}
