package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtDeclaration
import lexi.ir.IrDeclaration

object KtDeclarationVisitor extends Visitor[IrDeclaration] {
  override def visit(ast: Tree): IrDeclaration = {
    val ktDeclaration = ast.asInstanceOf[KtDeclaration]
    new IrDeclaration(
      classDeclaration = ktDeclaration.classDeclaration.map(KtClassVisitor.visit(_)),
      propertyDeclaration = ktDeclaration.propertyDeclaration.map(KtPropertyVisitor.visit(_)),
      functionDeclaration = ktDeclaration.functionDeclaration.map(KtFunctionVisitor.visit(_))
    )
  }
}
