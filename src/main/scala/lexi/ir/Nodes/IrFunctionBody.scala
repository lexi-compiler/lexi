package lexi.ir.Nodes

case class IrFunctionBody(
  var block: Vector[String] = null,
  var expression: IrExpression = null
) extends IrNode
