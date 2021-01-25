package lexi.frontend.kotlin.ir
import lexi.frontend.kotlin.ast.{ASTNode, KtProperty}
import lexi.ir.nodes.IrProperty

object KtPropertyVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrProperty = {
    val ktProperty = ast.asInstanceOf[KtProperty]
    new IrProperty {
      name = ktProperty.name
      expression = ktProperty.expression
      dataType = ktProperty.dataType
    }
  }
}
