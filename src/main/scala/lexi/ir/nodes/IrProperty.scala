package lexi.ir.nodes

case class IrProperty(
  var name: Option[String] = None,
  var expression: Option[String] = None,
  var dataType: Option[String] = None
) extends IrNode
