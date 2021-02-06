package lexi.ir.nodes

case class IrCall(
  var call: IrFunction = null
) extends IrNode
