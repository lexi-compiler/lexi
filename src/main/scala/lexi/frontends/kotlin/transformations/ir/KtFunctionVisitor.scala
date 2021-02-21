package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtFunction
import lexi.ir.IrFunction

object KtFunctionVisitor extends Visitor[IrFunction] {
  override def visit(ast: Tree): IrFunction = {
    val function = ast.asInstanceOf[KtFunction]
    IrFunction(
      name = function.name,
      `type` = function.`type`,
      functionBody = function.functionBody.map(KtFunctionBodyVisitor.visit(_))
    )
  }
}
