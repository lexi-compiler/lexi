package lexi.ir.nodes

case class IrDeclaration(
  var propertyDeclaration: Option[IrProperty] = None,
  var functionDeclaration: Option[IrFunction] = None
) extends IrNode
