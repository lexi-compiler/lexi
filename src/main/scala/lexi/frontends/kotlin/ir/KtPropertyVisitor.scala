package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{Tree, KtProperty}
import lexi.ir.nodes.IrProperty

object KtPropertyVisitor extends KtVisitor {
  override def visit(ast: Tree): IrProperty = {
    val property = ast.asInstanceOf[KtProperty]
    IrProperty(
      name = property.name,
      expression = property.expression,
      dataType = property.dataType
    )
  }
}
