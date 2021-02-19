package lexi.ir.nodes

case class IrFile(
  name: String,
  topLevelObjects: Option[Vector[IrTopLevelObject]] = None
) extends IrTree
