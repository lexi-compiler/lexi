package lexi.ir.nodes

case class IrFunction(
  var name: Option[String] = None,
  var `type`: Option[String] = None,
  var functionBody: Option[IrFunctionBody] = None
) extends IrNode
