package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtFunction
import lexi.ir.IrFunction

object KtFunctionVisitor extends Visitor[KtFunction, IrFunction] {
  override def visit(ast: KtFunction): IrFunction = {
    IrFunction(
      name = ast.name,
      `type` = ast.`type`,
      functionBody = ast.functionBody.map(KtFunctionBodyVisitor.visit(_))
    )
  }
}
