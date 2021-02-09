package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtFunction}
import lexi.ir.nodes.IrFunction

object KtFunctionVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrFunction =
    (
      (function: KtFunction) =>
        new IrFunction {
          name = function.name
          `type` = function.`type`
          functionBody = function.functionBody.map(KtFunctionBodyVisitor.visit(_))
        }
    )(ast.asInstanceOf[KtFunction])
}
