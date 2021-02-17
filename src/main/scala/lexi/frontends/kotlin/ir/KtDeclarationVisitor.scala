package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{Tree, KtDeclaration}
import lexi.ir.nodes.IrDeclaration

object KtDeclarationVisitor extends KtVisitor {
  override def visit(ast: Tree): IrDeclaration = {
    val ktDeclaration = ast.asInstanceOf[KtDeclaration]
    new IrDeclaration(
      classDeclaration = ktDeclaration.classDeclaration.map(KtClassVisitor.visit(_)),
      propertyDeclaration = ktDeclaration.propertyDeclaration.map(KtPropertyVisitor.visit(_)),
      functionDeclaration = ktDeclaration.functionDeclaration.map(KtFunctionVisitor.visit(_))
    )
  }
}
