package lexi.ir.nodes

case class IrClassBody(
  declarations: Option[Vector[IrDeclaration]] = None
) extends IrNode
