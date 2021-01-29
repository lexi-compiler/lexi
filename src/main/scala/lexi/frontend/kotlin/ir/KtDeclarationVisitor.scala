package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtDeclaration}
import lexi.ir.nodes.IrDeclaration

object KtDeclarationVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrDeclaration =
    (
      (declaration: KtDeclaration) =>
        new IrDeclaration {
          propertyDeclaration =
            if (null != declaration.propertyDeclaration)
              KtPropertyVisitor.visit(declaration.propertyDeclaration)
            else null
          functionDeclaration =
            if (null != declaration.functionDeclaration)
              KtFunctionVisitor.visit(declaration.functionDeclaration)
            else null
        }
    )(ast.asInstanceOf[KtDeclaration])
}
