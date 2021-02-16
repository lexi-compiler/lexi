package lexi.ir.nodes

case class IrFunction(
  name: Option[String] = None,
  `type`: Option[String] = None,
  functionBody: Option[IrFunctionBody] = None
) extends IrNode
