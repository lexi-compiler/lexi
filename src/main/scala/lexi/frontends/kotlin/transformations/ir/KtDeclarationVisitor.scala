package lexi.frontends.kotlin.transformations.ir

import lexi.frontends.kotlin.{AST, KtDeclaration}
import lexi.ir.IrDeclaration

object KtDeclarationVisitor extends KtVisitor {
  override def visit(ast: AST): IrDeclaration = {
    val ktDeclaration = ast.asInstanceOf[KtDeclaration]
    new IrDeclaration(
      classDeclaration = ktDeclaration.classDeclaration.map(KtClassVisitor.visit(_)),
      propertyDeclaration = ktDeclaration.propertyDeclaration.map(KtPropertyVisitor.visit(_)),
      functionDeclaration = ktDeclaration.functionDeclaration.map(KtFunctionVisitor.visit(_))
    )
  }
}
