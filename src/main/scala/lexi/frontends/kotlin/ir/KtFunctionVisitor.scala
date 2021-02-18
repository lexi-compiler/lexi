package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.{AST, KtFunction}
import lexi.ir.nodes.IrFunction

object KtFunctionVisitor extends KtVisitor {
  override def visit(ast: AST): IrFunction = {
    val function = ast.asInstanceOf[KtFunction]
    IrFunction(
      name = function.name,
      `type` = function.`type`,
      functionBody = function.functionBody.map(KtFunctionBodyVisitor.visit(_))
    )
  }
}
