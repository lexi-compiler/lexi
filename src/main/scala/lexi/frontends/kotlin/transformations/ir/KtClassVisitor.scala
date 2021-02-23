package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtClass
import lexi.ir.IrClass

import scala.util.Try

object KtClassVisitor extends Visitor[KtClass, IrClass] {
  override def visit(ast: KtClass): IrClass = {
    new IrClass(
      name = ast.name,
      classBody = ast.classBody.map(KtClassBodyVisitor.visit(_))
    )
  }
}
