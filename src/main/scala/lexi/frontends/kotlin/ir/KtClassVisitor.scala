package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{ASTNode, KtClass}
import lexi.ir.nodes.{IrClass, IrDeclaration}

import scala.util.Try

object KtClassVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrClass =
    (
      (ktClass: KtClass) =>
        new IrClass {
          name = ktClass.name
          classBody = ktClass.classBody.map(KtClassBodyVisitor.visit(_))
        }
    )(ast.asInstanceOf[KtClass])
}