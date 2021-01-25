package lexi.ir.nodes

case class IrProperty(
  var name: String = null,
  var expression: String = null,
  var dataType: String = null
) extends IrNode
