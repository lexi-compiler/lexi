package lexi.ir.nodes

case class IrClass(
  var name: Option[String] = None,
  var classBody: Option[IrClassBody] = None
) extends IrNode
