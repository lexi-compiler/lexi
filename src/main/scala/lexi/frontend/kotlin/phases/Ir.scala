package lexi.frontend.kotlin.phases

import lexi.Phase
import lexi.frontend.kotlin.ast.ASTNode
import lexi.ir.nodes.{IrExpression, IrFunction, IrFunctionBody, IrNode}

object Ir extends Phase {
  def apply(ast: ASTNode): IrNode = {
    IrFunction(
      name = "hello",
      `type` = "String",
      functionBody = IrFunctionBody(
        expression = IrExpression()
      )
    )
  }
}
