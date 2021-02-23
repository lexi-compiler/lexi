package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtTopLevelObject
import lexi.ir.IrTopLevelObject

object KtTopLevelObjectVisitor extends Visitor[KtTopLevelObject, IrTopLevelObject] {
  override def visit(ast: KtTopLevelObject): IrTopLevelObject = {
    IrTopLevelObject(
      declaration = ast.declaration.map(KtDeclarationVisitor.visit(_))
    )
  }
}
