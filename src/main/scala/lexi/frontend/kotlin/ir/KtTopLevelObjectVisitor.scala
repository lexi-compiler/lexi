package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtTopLevelObject}
import lexi.ir.nodes.IrTopLevelObject

object KtTopLevelObjectVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrTopLevelObject = {
    val tlo = ast.asInstanceOf[KtTopLevelObject]
    new IrTopLevelObject {
      declaration = KtDeclarationVisitor.visit(tlo.declaration)
    }
  }
}
