package lexi.frontends.kotlin.transformations.ir

import lexi.{Tree, Visitor}
import lexi.frontends.kotlin.KtProperty
import lexi.ir.IrProperty

object KtPropertyVisitor extends Visitor[IrProperty] {
  override def visit(ast: Tree): IrProperty = {
    val property = ast.asInstanceOf[KtProperty]
    IrProperty(
      name = property.name,
      expression = property.expression,
      dataType = property.dataType
    )
  }
}
