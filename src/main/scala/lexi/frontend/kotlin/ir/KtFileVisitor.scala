package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtFile, KtTopLevelObject}
import lexi.ir.nodes.IrFile

object KtFileVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrFile = {
    val ktFile = ast.asInstanceOf[KtFile]
    new IrFile {
      topLevelObject = ktFile.topLevelObjects.map { tlo: KtTopLevelObject =>
        KtTopLevelObjectVisitor.visit(tlo)
      }
    }
  }
}
