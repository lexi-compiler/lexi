package lexi.ir.nodes

case class IrFunction(
  var name: String = null,
  var `type`: String = null,
  var functionBody: IrFunctionBody = null
) extends IrNode
