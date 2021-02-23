package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.{AST, KtDeclaration}
import lexi.ir.IrDeclaration

object KtDeclarationVisitor extends Visitor[KtDeclaration, IrDeclaration] {
  override def visit(ast: KtDeclaration): IrDeclaration = {
    new IrDeclaration(
      classDeclaration = ast.classDeclaration.map(KtClassVisitor.visit(_)),
      propertyDeclaration = ast.propertyDeclaration.map(KtPropertyVisitor.visit(_)),
      functionDeclaration = ast.functionDeclaration.map(KtFunctionVisitor.visit(_))
    )
  }
}
