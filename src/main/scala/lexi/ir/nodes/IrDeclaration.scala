package lexi.ir.nodes

case class IrDeclaration(
  var classDeclaration: Option[IrClass] = None,
  var propertyDeclaration: Option[IrProperty] = None,
  var functionDeclaration: Option[IrFunction] = None
) extends IrNode
