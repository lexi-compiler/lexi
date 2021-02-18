package lexi.frontends.kotlin.transformations.ir

import lexi.frontends.kotlin.{AST, KtFile}
import lexi.ir.nodes.IrFile

import scala.util.Try

object KtFileVisitor extends KtVisitor {
  override def visit(ast: AST): IrFile =
    (
      (ktFile: KtFile) =>
        new IrFile(
          name = ktFile.name.get,
          topLevelObjects = ktFile.topLevelObjects.map(_.map(KtTopLevelObjectVisitor.visit(_)))
        )
    )(ast.asInstanceOf[KtFile])
}
