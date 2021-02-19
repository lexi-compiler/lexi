package lexi.frontends.kotlin.transformations.ir

import lexi.frontends.kotlin.{AST, KtTopLevelObject}
import lexi.ir.IrTopLevelObject

object KtTopLevelObjectVisitor extends KtVisitor {
  override def visit(ast: AST): IrTopLevelObject =
    (
      (topLevel: KtTopLevelObject) =>
        IrTopLevelObject(
          declaration = topLevel.declaration.map(KtDeclarationVisitor.visit(_))
        )
    )(ast.asInstanceOf[KtTopLevelObject])
}
