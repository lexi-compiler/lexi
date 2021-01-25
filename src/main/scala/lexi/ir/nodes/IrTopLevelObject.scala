package lexi.ir.nodes

case class IrTopLevelObject(
  var declaration: IrDeclaration = null
) extends IrNode
