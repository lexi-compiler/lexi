package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtFile
import lexi.ir.IrFile

import scala.util.Try

object KtFileVisitor extends Visitor {
  override def visit(tree: Tree): IrFile = {
    val ktFile = tree.asInstanceOf[KtFile]
    new IrFile(
      name = ktFile.name.get,
      topLevelObjects = ktFile.topLevelObjects.map(_.map(KtTopLevelObjectVisitor.visit(_)))
    )
  }
}
