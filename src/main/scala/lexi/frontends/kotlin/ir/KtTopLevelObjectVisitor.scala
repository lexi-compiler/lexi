package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{ASTNode, KtTopLevelObject}
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
