package lexi.ir.nodes

case class IrProperty(
  name: Option[String] = None,
  expression: Option[String] = None,
  dataType: Option[String] = None
) extends IrTree
