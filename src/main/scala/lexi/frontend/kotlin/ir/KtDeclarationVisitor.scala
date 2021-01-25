package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtDeclaration}
import lexi.ir.nodes.IrDeclaration

object KtDeclarationVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrDeclaration = {
    val ktDeclaration = ast.asInstanceOf[KtDeclaration]
    new IrDeclaration {
      propertyDeclaration =
        if (null != ktDeclaration.propertyDeclaration)
          KtPropertyVisitor.visit(ktDeclaration.propertyDeclaration)
        else null
      functionDeclaration =
        if (null != ktDeclaration.functionDeclaration)
          KtFunctionVisitor.visit(ktDeclaration.functionDeclaration)
        else null
    }
  }
}
