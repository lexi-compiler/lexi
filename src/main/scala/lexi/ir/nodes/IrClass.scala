package lexi.ir.nodes

case class IrClass(
  name: Option[String] = None,
  classBody: Option[IrClassBody] = None
) extends IrNode
