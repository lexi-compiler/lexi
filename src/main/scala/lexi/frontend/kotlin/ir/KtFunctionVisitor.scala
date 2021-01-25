package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtFunction}
import lexi.ir.nodes.IrFunction

object KtFunctionVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrFunction = {
    val ktFunction = ast.asInstanceOf[KtFunction]
    new IrFunction {
      name = ktFunction.name
      `type` = ktFunction.`type`
      functionBody = KtFunctionBodyVisitor.visit(ktFunction.functionBody)
    }
  }
}
