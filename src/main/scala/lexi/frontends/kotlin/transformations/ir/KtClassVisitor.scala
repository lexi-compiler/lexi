package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtClass
import lexi.ir.IrClass

import scala.util.Try

object KtClassVisitor extends Visitor[IrClass] {
  override def visit(ast: Tree): IrClass = {
    val ktClass = ast.asInstanceOf[KtClass]
    new IrClass(
      name = ktClass.name,
      classBody = ktClass.classBody.map(KtClassBodyVisitor.visit(_))
    )
  }
}
