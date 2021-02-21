package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtTopLevelObject
import lexi.ir.IrTopLevelObject

object KtTopLevelObjectVisitor extends Visitor {
  override def visit(ast: Tree): IrTopLevelObject = {
    val tlo = ast.asInstanceOf[KtTopLevelObject]
    IrTopLevelObject(
      declaration = tlo.declaration.map(KtDeclarationVisitor.visit(_))
    )
  }
}
