package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtProperty
import lexi.ir.IrProperty

object KtPropertyVisitor extends Visitor[KtProperty, IrProperty] {
  override def visit(tree: KtProperty): IrProperty = {
    IrProperty(
      name = tree.name,
      expression = tree.expression,
      dataType = tree.dataType
    )
  }
}
