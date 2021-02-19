package lexi.ir.nodes

case class IrTopLevelObject(
  declaration: Option[IrDeclaration] = None
) extends IrTree
