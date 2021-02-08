package lexi.ir.nodes

case class IrFile(
  var name: Option[String] = None,
  var topLevelObjects: Option[Vector[IrTopLevelObject]] = None
) extends IrNode
