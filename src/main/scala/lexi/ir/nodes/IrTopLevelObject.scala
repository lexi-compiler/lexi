package lexi.ir.nodes

case class IrTopLevelObject(
  var declaration: Option[IrDeclaration] = None
) extends IrNode
