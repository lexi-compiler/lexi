package lexi.ir.nodes

case class IrFunctionBody(
  var block: Vector[String] = null,
  var expression: IrExpression = null
) extends IrNode
