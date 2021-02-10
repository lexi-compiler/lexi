package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{ASTNode, KtProperty}
import lexi.ir.nodes.IrProperty

object KtPropertyVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrProperty =
    (
      (property: KtProperty) =>
        IrProperty(
          name = property.name,
          expression = property.expression,
          dataType = property.dataType
        )
    )(ast.asInstanceOf[KtProperty])
}
