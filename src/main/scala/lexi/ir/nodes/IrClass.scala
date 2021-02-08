package lexi.ir.nodes

case class IrClass(
  var name: Option[String] = None,
  var methods: Option[Vector[IrFunction]] = None
) extends IrNode
