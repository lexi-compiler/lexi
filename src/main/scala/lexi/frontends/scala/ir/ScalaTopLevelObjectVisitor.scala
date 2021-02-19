package lexi.frontends.scala.ir

import lexi.ir.IrTopLevelObject

import scala.meta._

object ScalaTopLevelObjectVisitor extends ScalaVisitor {
  override def visit(tree: Tree): IrTopLevelObject = {
    IrTopLevelObject(
//      declaration = tree.declaration.map(ScalaDeclarationVisitor.visit(_))
    )
  }
}
