package lexi.frontends.kotlin.transformations.ir

import lexi.frontends.kotlin.{AST, KtClass}
import lexi.ir.nodes.{IrClass, IrDeclaration}

import scala.util.Try

object KtClassVisitor extends KtVisitor {
  override def visit(ast: AST): IrClass = {
    val ktClass = ast.asInstanceOf[KtClass]
    new IrClass(
      name = ktClass.name,
      classBody = ktClass.classBody.map(KtClassBodyVisitor.visit(_))
    )
  }
}
