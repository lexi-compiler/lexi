package lexi.ir.nodes

case class IrFunctionBody(
  var block: Option[Vector[String]] = None,
  var expression: Option[IrExpression] = None
) extends IrNode
