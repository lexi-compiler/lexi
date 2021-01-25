package lexi.ir.nodes

case class IrDeclaration(
  var propertyDeclaration: IrProperty = null,
  var functionDeclaration: IrFunction = null
) extends IrNode
