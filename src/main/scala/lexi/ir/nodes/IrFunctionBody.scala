package lexi.ir.nodes

case class IrFunctionBody(
  block: Option[Vector[String]] = None,
  expression: Option[IrExpression] = None
) extends IrTree
