package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtTopLevelObject}
import lexi.ir.nodes.IrTopLevelObject

object KtTopLevelObjectVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrTopLevelObject =
    (
      (topLevel: KtTopLevelObject) =>
        IrTopLevelObject(
          declaration = topLevel.declaration.map(KtDeclarationVisitor.visit(_))
        )
    )(ast.asInstanceOf[KtTopLevelObject])
}
