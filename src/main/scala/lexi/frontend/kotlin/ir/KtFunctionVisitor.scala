package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtFunction}
import lexi.ir.nodes.IrFunction

object KtFunctionVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrFunction =
    (
      (function: KtFunction) =>
        IrFunction(
          name = function.name,
          `type` = function.`type`,
          functionBody = KtFunctionBodyVisitor.visit(function.functionBody)
        )
    )(ast.asInstanceOf[KtFunction])
}
