package lexi.ir.nodes

case class IrClassBody(
  var declarations: Option[Vector[IrDeclaration]] = None
) extends IrNode
