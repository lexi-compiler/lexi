package lexi.frontend.kotlin.ir

import lexi.frontend.kotlin.ast.{ASTNode, KtFile}
import lexi.ir.nodes.IrFile

object KtFileVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrFile =
    (
      (ktFile: KtFile) =>
        IrFile(topLevelObjects =
          ktFile.topLevelObjects.map(KtTopLevelObjectVisitor.visit(_))
        )
    )(ast.asInstanceOf[KtFile])
}
