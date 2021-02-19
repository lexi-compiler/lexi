package lexi.ir.nodes

case class IrCall(
  var call: Option[IrFunction] = None
) extends IrTree
