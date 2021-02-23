package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.{AST, KtFile}
import lexi.ir.IrFile

import scala.util.Try

object KtFileVisitor extends Visitor[KtFile, IrFile] {
  override def visit(ast: KtFile): IrFile = {
    new IrFile(
      name = ast.name.get,
      topLevelObjects = ast.topLevelObjects.map(_.map(KtTopLevelObjectVisitor.visit(_)))
    )
  }
}
