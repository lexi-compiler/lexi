package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{ASTNode, KtDeclaration}
import lexi.ir.nodes.IrDeclaration

object KtDeclarationVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrDeclaration =
    (
      (ktDeclaration: KtDeclaration) =>
        new IrDeclaration {
          propertyDeclaration = ktDeclaration.propertyDeclaration.map(KtPropertyVisitor.visit(_))
          functionDeclaration = ktDeclaration.functionDeclaration.map(KtFunctionVisitor.visit(_))
        }
    )(ast.asInstanceOf[KtDeclaration])
}
