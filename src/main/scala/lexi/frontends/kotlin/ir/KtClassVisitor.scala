package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{Tree, KtClass}
import lexi.ir.nodes.{IrClass, IrDeclaration}

import scala.util.Try

object KtClassVisitor extends KtVisitor {
  override def visit(ast: Tree): IrClass = {
    val ktClass = ast.asInstanceOf[KtClass]
    new IrClass(
      name = ktClass.name,
      classBody = ktClass.classBody.map(KtClassBodyVisitor.visit(_))
    )
  }
}
