package lexi.ir.nodes

case class IrFile(
  var name: String = null,
  var topLevelObjects: Vector[IrTopLevelObject] = Vector.empty
) extends IrNode
