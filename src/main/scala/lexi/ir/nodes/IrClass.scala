package lexi.ir.nodes

case class IrClass(
  var name: String = null,
  var methods: Vector[IrFunction] = null
) extends IrNode
