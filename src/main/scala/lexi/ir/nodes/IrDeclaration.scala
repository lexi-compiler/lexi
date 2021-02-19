package lexi.ir.nodes

case class IrDeclaration(
  classDeclaration: Option[IrClass] = None,
  propertyDeclaration: Option[IrProperty] = None,
  functionDeclaration: Option[IrFunction] = None
) extends IrTree
