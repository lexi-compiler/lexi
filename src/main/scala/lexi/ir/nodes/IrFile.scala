package lexi.ir.nodes

case class IrFile(
  var name: String = null,
  var topLevelObject: Vector[IrTopLevelObject] = Vector.empty
) extends IrNode
